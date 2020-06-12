package com.shuyue.finance_server.service.thirdparty.yonyou;

import com.shuyue.finance_server.aspect.AspectMethod;
import com.shuyue.finance_server.dto.request.thirdparty.yongyou.*;
import com.shuyue.finance_server.dto.response.thirdparty.YonyouVoucherResponse;
import com.shuyue.finance_server.util.JaxbUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author haohua.rong
 * @description
 * @contact 13512835407@163.com
 * @date 2020-03-15
 */
@Slf4j
@Service("yonyouVoucherService")
public class YonyouVoucherService {

    @Value("${shuyue.service.pushYonyouUrl}")
    private String url;

    @AspectMethod("同步客户数据到NCC")
    public YonyouVoucherResponse syncCustomer(UfinterfaceDtoRequest ufinterface) {
        String requestData = JaxbUtils.jaxbBeanToxml(ufinterface);
        try {
            log.info("开始推送客户数据到NCC{}", requestData);
            String response = pushRequest(url, requestData);
            log.info("推送客户到NCC响应 {}", response);
            return JaxbUtils.jaxbConvertXmlToBean(response, UfinterfaceDtoRequest.class).getSendresult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @AspectMethod("同步供应商数据到NCC")
    public YonyouVoucherResponse syncSupplier(UfinterfaceDtoRequest ufinterface) {
        String requestData = JaxbUtils.jaxbBeanToxml(ufinterface);
        System.out.println(requestData);
        try {
            log.info("开始推送供应商数据到NCC {}", requestData);
            String response = pushRequest(url, requestData);
            log.info("推送供应商到NCC响应 {}", response);
            return JaxbUtils.jaxbConvertXmlToBean(response, UfinterfaceDtoRequest.class).getSendresult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @AspectMethod("同步员工数据到NCC")
    public YonyouVoucherResponse syncEmployee(UfinterfaceDtoRequest ufinterface) {
        String requestData = JaxbUtils.jaxbBeanToxml(ufinterface);
        try {
            log.info("开始推送员工数据到NCC {}", requestData);
            String response = pushRequest(url, requestData);
            log.info("推送员工到NCC响应 {}", response);
            return JaxbUtils.jaxbConvertXmlToBean(response, UfinterfaceDtoRequest.class).getSendresult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @AspectMethod("同步部门数据到NCC")
    public YonyouVoucherResponse syncDept(UfinterfaceDtoRequest ufinterface) {
        String requestData = JaxbUtils.jaxbBeanToxml(ufinterface);
        try {
            log.info("开始推送部门数据到NCC {}", requestData);
            String response = pushRequest(url, requestData);
            log.info("推送部门数据到NCC响应 {}", response);
            return JaxbUtils.jaxbConvertXmlToBean(response, UfinterfaceDtoRequest.class).getSendresult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @AspectMethod("同步结算方式数据到NCC")
    public YonyouVoucherResponse syncBalatype(UfinterfaceDtoRequest ufinterface) {
        String requestData = JaxbUtils.jaxbBeanToxml(ufinterface);
        try {
            log.info("开始推送结算方式数据到NCC {}", requestData);
            String response = pushRequest(url, requestData);
            log.info("推送结算方式到NCC响应 {}", response);
            return JaxbUtils.jaxbConvertXmlToBean(response, UfinterfaceDtoRequest.class).getSendresult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @AspectMethod("同步凭证数据到NCC")
    public YonyouVoucherResponse syncVoucher(UfinterfaceDtoRequest ufinterface) {
        String requestData = JaxbUtils.jaxbBeanToxml(ufinterface);
        try {
            log.info("开始推送凭证数据到NCC {}", requestData);
            String response = pushRequest(url, requestData);
            log.info("推送凭证数据到NCC响应 {}", response);
            return JaxbUtils.jaxbConvertXmlToBean(response, UfinterfaceDtoRequest.class).getSendresult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String pushRequest(String url, String requestData) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return restTemplate.postForObject(url, new HttpEntity<>(requestData, headers), String.class);
    }

}
