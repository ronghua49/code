package com.forezp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ---------------------------
 * 微服务实例信息 (entityInfo)         
 * ---------------------------
 * 作者：  宋建国
 * @date 2019-04-30 17:23:11
 * @since jdk8
 * ---------------------------
 */
@ApiModel(value = "entityInfo",description = "微服务中实体类名信息") 
public class entityInfo {
	/**微服务Id*/
	@ApiModelProperty(value="微服务Id，表示此实体类属于哪个微服务")
	private String serviceId;
	/**实体类名称*/
	@ApiModelProperty(value="包括实体类名称")
	private String entityName;
	
	@ApiModelProperty(value="不包括实体类名称")
	private String className;
	
	@ApiModelProperty(value="服务网址")
	private String httpUrl;
	
	public entityInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public entityInfo(String serviceId, String entityName, String className, String httpUrl) {
		super();
		this.serviceId = serviceId;
		this.entityName = entityName;
		this.className = className;
		this.httpUrl = httpUrl;
	}
	
	public String getHttpUrl() {
		return httpUrl;
	}
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getEntityName() {
		return entityName;
	}	
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
