package com.winway.scm.controller;

import com.hotent.base.controller.BaseController;
import com.winway.scm.ConfigProperties;
import com.winway.scm.model.UploadUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableConfigurationProperties(ConfigProperties.class)
@RestController
public class Test extends BaseController {

    @Autowired
    ConfigProperties configProperties;

    @GetMapping("/test")
    @ApiOperation(value="集中发货商品表数据列表", httpMethod = "GET", notes = "获取集中发货商品表列表")
    public String  test(){
        System.out.println(UploadUtil.URL);
        return UploadUtil.URL;
    }


    @GetMapping("/test1")
    @ApiOperation(value="集中发货商品表数据列表", httpMethod = "GET", notes = "获取集中发货商品表列表")
    public String  test1(){
        System.out.println(configProperties.getUploadPicUrl());
        System.out.println(configProperties.getUploadFilesUrl());
        return "success";
    }
}
