package com.haohua.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.haohua.entity.Order;
@Component
public interface OrderService {

	List<Order> findAllOrders();

	Order findOrderById(Integer orderId);

	void updateOrder(Order movie);

	void addOrder(Order movie);

	void delOrderById(Integer id);

}
