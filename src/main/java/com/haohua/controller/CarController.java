package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/10/20
 */

import com.haohua.entity.Car;
import com.haohua.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carservice;

    @GetMapping("/car")
    public List<Car> findAllCarINfo() {
        return carservice.findAllCar();
    }


    @GetMapping("/test")
    public String test(){
        return  "你好";
    }



}
