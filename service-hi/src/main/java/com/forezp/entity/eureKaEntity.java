package com.forezp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ---------------------------
 * 微服务实例信息 (EureKaEntity)         
 * ---------------------------
 * 作者：  宋建国
 * @date 2019-04-30 17:23:11
 * @since jdk8
 * ---------------------------
 */
@ApiModel(value = "EureKaEntity",description = "EureKa服务器注册的，微服务实例信息") 
public class eureKaEntity {
	private static final long serialVersionUID = 1L;
	/**微服务IP*/
	@ApiModelProperty(value="微服务IP")
	private String host;
	/**微服务端口号*/
	@ApiModelProperty(value="微服务端口号")
	private String port;
	/**微服务Id*/
	@ApiModelProperty(value="微服务Id")
	private String serviceId;
	/**微服务网址*/
	@ApiModelProperty(value="微服务网址")
	private String uri;
		
	public eureKaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public eureKaEntity(String host, String port, String serviceId, String uri2) {
		super();
		this.host = host;
		this.port = port;
		this.serviceId = serviceId;
		this.uri = uri2;
	}
	public String getHost() {
		return host;
	}
	public String getPort() {
		return port;
	}
	public String getServiceId() {
		return serviceId;
	}
	public String getUri() {
		return uri;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}	
}
