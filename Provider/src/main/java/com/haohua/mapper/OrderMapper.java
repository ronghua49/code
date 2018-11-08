package com.haohua.mapper;

import com.haohua.entity.Car;
import com.haohua.entity.Order;
import com.haohua.entity.OrderExample;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findYestardayOrder(@Param("state") String orderStateDone, @Param("dateTime") String yesterdayDate);

    void saveOrder(@Param("orderMoney") BigDecimal orderMoney, @Param("state")String state,@Param("carId") Integer carId, @Param("serviceTypeId")Integer serviceTypeId,@Param("employeeId") int employeeId);


    void sumOrderMoney(Map<String,Object> paramMap);
}