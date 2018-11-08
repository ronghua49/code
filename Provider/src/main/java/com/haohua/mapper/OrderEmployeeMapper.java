package com.haohua.mapper;


import com.haohua.entity.OrderEmployee;
import com.haohua.entity.OrderEmployeeExample;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface OrderEmployeeMapper {
    long countByExample(OrderEmployeeExample example);

    int deleteByExample(OrderEmployeeExample example);

    int insert(OrderEmployee record);

    int insertSelective(OrderEmployee record);

    List<OrderEmployee> selectByExample(OrderEmployeeExample example);

    int updateByExampleSelective(@Param("record") OrderEmployee record, @Param("example") OrderEmployeeExample example);

    int updateByExample(@Param("record") OrderEmployee record, @Param("example") OrderEmployeeExample example);

    Integer findEmployeeIdByOrderId(Integer orderId);
}