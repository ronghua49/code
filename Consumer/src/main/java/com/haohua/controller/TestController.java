package com.haohua.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.haohua.client.OrderConsumerClient;
import com.haohua.entity.Dog;
import com.haohua.entity.Order;

@Controller
public class TestController {
	
	@Autowired
	private OrderConsumerClient orderClient;
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "success";
	}
	
	@GetMapping("/test/templates")
	public String testTemplates(Model model) {
		model.addAttribute("message", "test success");
		Dog dog = new Dog();
		dog.setId(1);
		dog.setName("rose");
		Dog dog2 = new Dog();
		dog2.setId(2);
		dog2.setName("jack");
		ArrayList<Dog> dogList = Lists.newArrayList(dog,dog2);
		model.addAttribute("dogList", dogList);
		return "test";
	}
	
	
	@GetMapping("/consumer/{orderId:\\d+}")
	@ResponseBody
	public Order findById(@PathVariable Integer orderId) {
		System.out.println("------------------------");
		return orderClient.findOrderById(orderId);
	}
	
	@GetMapping
	@ResponseBody
	public List<Order> findAllList(){
		return orderClient.findAllOrders();
	}
	
	@PostMapping("/consumer/order/add")
	public void addOrder(@RequestBody Order order) {
		orderClient.saveOrder(order);
	}
	
	@GetMapping("/consumer/del/{id:\\d+}")
	public void delOrderByid(@PathVariable Integer id) {
		orderClient.delOrderById(id);
	}
	
	@GetMapping("/order.html")
	public ModelAndView findAllOrders() {
		List<Order> orderList = orderClient.findAllOrders();
		ModelAndView modelAndView = new ModelAndView("order/order");
		modelAndView.addObject("orderList", orderList);
		return modelAndView;
				
	}

	@GetMapping("/order")
	public String findAllOrders(Model model) {
		List<Order> orderList = orderClient.findAllOrders();
		
		model.addAttribute("orderList", orderList);
		return "order/order";
				
	}
	
	@GetMapping("/layout")
	public String layOut() {
		return "layout";
	}
}
