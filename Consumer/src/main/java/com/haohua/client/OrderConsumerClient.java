package com.haohua.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.haohua.entity.Order;

@FeignClient(name="ORDER-PRIVODER")
public interface OrderConsumerClient {
	
	//反向参数绑定
	@GetMapping("/order/{orderId}")
	Order findOrderById(@PathVariable(name="orderId") Integer orderId);
	//反向参数绑定，若访问参数为空必须指定 "/"
	@GetMapping("/")
	public List<Order> findAllOrders();
	
	@GetMapping("/del/{id}")
	public void delOrderById(@PathVariable(name="id") Integer id);
	
	@PostMapping("/save")
	public void saveOrder(@RequestBody Order order);
	

}
