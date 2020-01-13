package com.forezp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ---------------------------
 * 微服务实体类属性信息 (classInfo)         
 * ---------------------------
 * 作者：  宋建国
 * @date 2019-04-30 17:23:11
 * @since jdk8
 * ---------------------------
 */
@ApiModel(value = "ClassInfo",description = "实体类属性信息")
public class classInfo {
	@ApiModelProperty(value="实体类名称")
	private String className;
	@ApiModelProperty(value="实体类属性")
	private String fieldName;
	@ApiModelProperty(value="实体类类型")
	private String fieldType;
	/**注解*/
	@ApiModelProperty(value="字段注解")
	private String annotation;

	
	public classInfo(String className, String fieldName, String fieldType) {
		super();
		this.className = className;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}

	public classInfo(String className, String fieldName, String fieldType, String annotation) {
		this.className = className;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.annotation = annotation;
	}

	public classInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getClassName() {
		return className;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
}
