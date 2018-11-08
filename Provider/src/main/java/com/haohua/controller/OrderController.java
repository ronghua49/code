package com.haohua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.haohua.entity.Order;
import com.haohua.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order/{orderId:\\d+}")
	public Order findOrderById(@PathVariable Integer orderId) {
		return orderService.findOrderById(orderId);
	}
	
	//作为提供者，必须指定访问路径，为了接口的反向路径绑定
	@GetMapping("/")
	public List<Order> findAllOrders(){
		return orderService.findAllOrders();
	}
	
	@GetMapping("/del/{id:\\d+}")
	public void delOrderById(@PathVariable Integer id) {
		orderService.delOrderById(id);
	}
	
	@PostMapping("/save")
	public void saveOrder(@RequestBody Order order) {
		orderService.addOrder(order);
	}
	
	

}
