package com.winway.scm.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadUtil {

    public static  String URL;


    @Value("${scan.url}")// application.properties  自带配置文件中属性
    public void setUrl(String url){
        URL=url;
    }
}
