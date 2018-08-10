package com.haohua.erp.controllor;    /*
 * @author  Administrator
 * @date 2018/8/3
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.haohua.erp.entity.*;
import com.haohua.erp.entity.VO.OrderEditTransInfo;
import com.haohua.erp.entity.VO.OrderVo;
import com.haohua.erp.exception.ServiceException;
import com.haohua.erp.service.OrderService;
import com.haohua.erp.serviceImp.OrderServiceImpl;
import com.haohua.erp.util.JsonResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
@Autowired
private OrderService orderService;

    @GetMapping("/new")
    public String newOrder(){
        return "order/newOrder";
    }
    @PostMapping("/new")
    @ResponseBody
    public JsonResponse newOrder(String json){
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(json,OrderVo.class);
        

       Subject subject = SecurityUtils.getSubject();
       Employee employee= (Employee) subject.getPrincipal();
        //得到订单详情，新增订单
        try{
            orderService.addOrder(orderVo,employee.getId());
            return  JsonResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            return  JsonResponse.error();
        }
    }
    @GetMapping("/serviceType")
    @ResponseBody
    public JsonResponse allServiceType(){
        List<ServiceType> serviceTypeList=  orderService.findServiceTypeList();
        return JsonResponse.success(serviceTypeList);
    }

    @GetMapping("/partsType")
    @ResponseBody
    public JsonResponse allPartsType(){
        List<Type> typeList = orderService.findPartsTypeList();
        return  JsonResponse.success(typeList);
    }

    @GetMapping("/{typeId:\\d+}/parts")
    @ResponseBody
    public JsonResponse findPartsListByTypeId(@PathVariable Integer typeId){
        //根据零件类型id 查询下面库存大于0的零部件
        List<Parts> partsList = orderService.findPartsListByTypeId(typeId);
        return JsonResponse.success(partsList);
    }
    /**
     * 订单查询
     * @param licenceNo
     * @param tel
     * @param orderTime
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String findOrderList(@RequestParam(required = false) String licenceNo,
                                    @RequestParam(required = false) String tel,
                                    @RequestParam(required = false) String orderTime,
                                    @RequestParam(required = false) String state,
                                     @RequestParam (defaultValue = "1")Integer p,
                                     Model model ){
        Map<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("licenceNo",licenceNo);
        paramMap.put("tel",tel);
        paramMap.put("orderTime",orderTime);
        paramMap.put("state",state);
        paramMap.put("p",p);
        try{
           PageInfo<Order> page = orderService.findOrdersByParamMap(paramMap);
            model.addAttribute("page",page);
        }catch (ServiceException e){
            model.addAttribute("message",e.getMessage());
        }
            return "order/orderList";
    }
    /**
     * 订单的详情查询
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/{orderId:\\d+}/detail")
    public String orderDetail(@PathVariable Integer orderId,Model model){
        //查询order详情
        Order order = orderService.findOrder(orderId);
        //查询车辆信息详情
        Car car = orderService.findCarInfoByOrderId(orderId);
        //查询服务信息详情
        ServiceType service = orderService.findServiceTypeByOrderId(orderId);
        //查询订单所需的零件信息详情
        List<Parts> partsList = orderService.findPartsListByOrderId(orderId);
        model.addAttribute("order",order);
        model.addAttribute("car",car);
        model.addAttribute("service",service);
        model.addAttribute("partsList",partsList);
        return "order/orderDetail";
    }

    /**
     * 根据订单id删除订单
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId:\\d+}/del")
    @ResponseBody
    public  JsonResponse delOrder(@PathVariable Integer orderId){
            try{
                orderService.delOrderById(orderId);
                return  JsonResponse.success();
            }catch (ServiceException e){
               return JsonResponse.error(e.getMessage());
            }
    }

    @GetMapping("/{orderId:\\d+}/editPage")
    public String editOrder(@PathVariable Integer orderId ,Model model){
        model.addAttribute("orderId",orderId);
        return "order/editOrder";

    }

    @GetMapping("/{orderId:\\d+}/edit")
    @ResponseBody
    public JsonResponse editOrder(@PathVariable Integer orderId ){
        //查询车辆信息
        Car car = orderService.findCarInfoByOrderId(orderId);
        //查询所定服务类型
        ServiceType chooseServiceType = orderService.findServiceTypeByOrderId(orderId);
        //查询所选的所有配件信息列表
        List<Parts> choosePartsList = orderService.findPartsListByOrderId(orderId);
        OrderEditTransInfo info = new OrderEditTransInfo();
        info.setOrderId(orderId);
        info.setCar(car);
        info.setChooseServiceType(chooseServiceType);
        info.setChoosePartsList(choosePartsList);
        return JsonResponse.success(info);
    }

    @PostMapping("/edit")
    @ResponseBody
    public JsonResponse editOrder(String json){
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(json,OrderVo.class);
        try{
            orderService.editOrder(orderVo);
            return  JsonResponse.success();
        }catch (ServiceException e){
            return  JsonResponse.error(e.getMessage());
        }
    }

    @GetMapping("/{orderId:\\d+}/trans")
    @ResponseBody
    public JsonResponse transOrder(@PathVariable Integer orderId){
        try{
            orderService.transOrderById(orderId);
            return  JsonResponse.success();
        }catch (ServiceException e){
            return JsonResponse.error(e.getMessage());
        }

    }




}