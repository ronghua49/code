package com.forezp;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "xyjconfig")
public class XyjconfigProperties {
	/**
	 * 标识日志数据库所在微服务,默认值为xyj-admin
	 * */
	private String logServiceId = "xyj-admin";
	/**
	 * 生成ID使用,数据中心编码[雪花算法],每个微服务需不同,取值范围 0~31,默认值0
	 * */
	private long datacenterId = 0;
	/**
	 * 生成ID使用,机器或进程编码[雪花算法],每个微服务需不同,取值范围 0~31,默认值0
	 * */
	private long workerId = 0;
	/**
	 * 标识审批流所在微服务,默认值为activity6
	 * */
	private String activitiServerId = "activity6";
	
	/**
	 * 标识可被扫描到的实体类所在包，用于讯宜捷前端框架
	 */
	private List<String> scanmodel = new ArrayList<String>(); 
	/**
	 * restTemplate请求超时时间,根据前端变化而变化
	 */
	private int restTemplateTimeOut = 50;
	/**
	 * http://192.168.3.111:8011/
	 */
	private  String uploadAddress = "";
	/**
	 * 日志保存机制 0:InfluxDB   1:Mysql
	 */
	private String logType = "0";

	public String getLogServiceId() {
		return logServiceId;
	}

	public void setLogServiceId(String logServiceId) {
		this.logServiceId = logServiceId;
	}

	public long getDatacenterId() {
		return datacenterId;
	}

	public void setDatacenterId(long datacenterId) {
		this.datacenterId = datacenterId;
	}

	public long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}

	public String getActivitiServerId() {
		return activitiServerId;
	}

	public void setActivitiServerId(String activitiServerId) {
		this.activitiServerId = activitiServerId;
	}

	public List<String> getScanmodel() {
		return scanmodel;
	}

	public void setScanmodel(List<String> scanmodel) {
		this.scanmodel = scanmodel;
	}

	public int getRestTemplateTimeOut() {
		return restTemplateTimeOut;
	}

	public void setRestTemplateTimeOut(int restTemplateTimeOut) {
		this.restTemplateTimeOut = restTemplateTimeOut;
	}

	public String getUploadAddress() {
		return uploadAddress;
	}

	public void setUploadAddress(String uploadAddress) {
		this.uploadAddress = uploadAddress;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
}
