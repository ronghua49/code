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
@ApiModel(value = "entityName",description = "微服务中实体类名信息,包括全名，类名") 
public class entityName {
	/**包括包名的类名称*/
	@ApiModelProperty(value="包括包名的类名称")
	private String classFullName;
	
	@ApiModelProperty(value="不包括包名的类名称")
	private String className;

	public entityName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public entityName(String classFullName, String className) {
		super();
		this.classFullName = classFullName;
		this.className = className;
	}
	public String getClassFullName() {
		return classFullName;
	}
	public String getclassName() {
		return className;
	}
	public void setClassFullName(String classFullName) {
		this.classFullName = classFullName;
	}
	public void setClassNmae(String className) {
		this.className = className;
	}
	
	
}
