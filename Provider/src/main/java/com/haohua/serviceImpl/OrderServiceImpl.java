package com.haohua.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haohua.entity.Order;
import com.haohua.mapper.OrderMapper;
import com.haohua.service.OrderService;
@Component
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<Order> findAllOrders() {
		
		return orderMapper.selectByExample(null) ;
	}

	@Override
	public Order findOrderById(Integer id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateOrder(Order movie) {
		orderMapper.updateByPrimaryKeySelective(movie);
		
	}

	@Override
	public void addOrder(Order order) {
		orderMapper.insertSelective(order);
		
	}

	@Override
	public void delOrderById(Integer id) {
		orderMapper.deleteByPrimaryKey(id);
		
	}

}
