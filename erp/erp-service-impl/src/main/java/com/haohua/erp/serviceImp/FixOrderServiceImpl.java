package com.haohua.erp.serviceImp;    /*
 * @author  Administrator
 * @date 2018/8/8
 */

import com.haohua.erp.entity.*;
import com.haohua.erp.entity.dto.OrderEmployeeDto;
import com.haohua.erp.entity.dto.OrderTransDto;
import com.haohua.erp.exception.ServiceException;
import com.haohua.erp.mapper.FixOrderMapper;
import com.haohua.erp.mapper.FixPartsMapper;
import com.haohua.erp.mapper.OrderEmployeeMapper;
import com.haohua.erp.service.FixOrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

@Service
public class FixOrderServiceImpl implements FixOrderService {

    @Autowired
    private FixOrderMapper fixOrderMapper;
    @Autowired
    private FixPartsMapper fixPartsMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private OrderEmployeeMapper orderEmployeeMapper;

    /**
     * 订单接受，领取配件
     *
     * @param orderId
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void acceptOrder(Integer orderId, Employee employee) {
        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(orderId);
        if (fixOrder != null) {
            //查询这个员工当下是否有未处理完成的订单
            FixOrder currFixOrder = fixOrderMapper.findCurrFixOrderByEmployeeId(employee.getId());
            if (currFixOrder == null) {
                //查询订单所需配件，封装发送对象
                List<FixParts> fixPartsList = fixPartsMapper.findFixPartsListByOrderId(orderId);

                OrderTransDto orderTransDto = new OrderTransDto();
                orderTransDto.setFixPartsList(fixPartsList);
                orderTransDto.setFixOrder(fixOrder);
                //发送所需配件和发消息人到仓库的web
                String orderJson = JSONObject.fromObject(orderTransDto).toString();
                jmsTemplate.send("partsWitingGet", new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(orderJson);
                    }
                });
                //发送订单和员工消息和订单状态到消息队列
                OrderEmployeeDto orderEmployeeDto = new OrderEmployeeDto();
                orderEmployeeDto.setEmployeeId(employee.getId());
                orderEmployeeDto.setOrderId(orderId);
                orderEmployeeDto.setOrderState(FixOrder.ORDER_STATE_DEALING);
                String json = JSONObject.fromObject(orderEmployeeDto).toString();
                jmsTemplate.send("orderState", new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(json);
                    }
                });
                //插入员工和维修订单关系
                fixOrder.setFixEmployeeId(employee.getId());
                fixOrder.setFixEmployeeName(employee.getEmployeeName());
                fixOrderMapper.updateByPrimaryKeySelective(fixOrder);
            } else {
                throw  new ServiceException("您有未完成的订单在处理，不得再次接单");
            }
        } else {
            throw new ServiceException("订单不存在");
        }
    }
    /**
     * 查询所有刚下发的维修订单
     *
     * @return
     */
    @Override
    public List<FixOrder> findFixOrderListWithParts() {
        return fixOrderMapper.findListWithParts();
    }

    /**
     * 根据维修订单id查询订单的详细信息
     * @param orderId
     * @return
     */
    @Override
    public FixOrder findFixOrderWithPartsById(Integer orderId) {
        return fixOrderMapper.findWithPartsById(orderId);
    }
    /**
     * 维修完成
     * @param orderId
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void getFixDone(Integer orderId) {
        //将fixorder状态改变为等待质检
        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(orderId);
        fixOrder.setState(FixOrder.ORDER_STATE_WITING_CHECK);
        fixOrderMapper.updateByPrimaryKeySelective(fixOrder);
        //将此订单状态发消息到前台
        OrderEmployeeDto orderEmployeeDto = new OrderEmployeeDto();
        orderEmployeeDto.setOrderState(FixOrder.ORDER_STATE_WITING_CHECK);
        orderEmployeeDto.setOrderId(orderId);
        String json = JSONObject.fromObject(orderEmployeeDto).toString();
        jmsTemplate.send("orderState", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });
    }

    /**
     * 查询所有等待质检的订单
     * @return
     */
    @Override
    public List<FixOrder> findCheckOrderListWithParts() {
        return fixOrderMapper.findCheckListWithParts();
    }

    /**
     * 领取质检任务
     * @param orderId
     * @param employee
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void getCheckTask(Integer orderId, Employee employee) {

        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(orderId);
        if(fixOrder!=null){
            if(fixOrder.getState().equals(FixOrder.ORDER_STATE_WITING_CHECK)){
                //改变订单状态，加入质检信息
                fixOrder.setState(FixOrder.ORDER_STATE_CHECKING);
                fixOrder.setCheckEmployeeId(employee.getId());
                fixOrder.setCheckEmployeeName(employee.getEmployeeName());
                fixOrderMapper.updateByPrimaryKeySelective(fixOrder);
                //发消息到消息队列
                OrderEmployeeDto orderEmployeeDto = new OrderEmployeeDto();
                orderEmployeeDto.setOrderId(orderId);
                orderEmployeeDto.setOrderState(FixOrder.ORDER_STATE_CHECKING);
                orderEmployeeDto.setEmployeeId(employee.getId());

                String json = JSONObject.fromObject(orderEmployeeDto).toString();

                jmsTemplate.send("orderState", new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(json);
                    }
                });
            }else {
                throw  new ServiceException("该订单状态不可质检");
            }
        }else {
            throw  new ServiceException("该订单不存在");
        }
    }

    /**
     *根据订单id查询要质检的订单详情
     * @param orderId
     */
    @Override
    public FixOrder findCheckOrderWithPartsById(Integer orderId) {
        return fixOrderMapper.findWithPartsById(orderId);
    }
}
