package com.forezp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "XyjControllerURL", description = "所有接口及URL")
public class XyjControllerURL {

	@ApiModelProperty(value = "服务")
	private String server;
	@ApiModelProperty(value = "类名")
	private String classname;
	@ApiModelProperty(value = "类URL")
	private String classurl;
	@ApiModelProperty(value = "类注释")
	private String classdesc;
	@ApiModelProperty(value = "方法名")
	private String methodname;
	@ApiModelProperty(value = "方面注释")
	private String methoddesc;
	@ApiModelProperty(value = "接口地址")
	private String methodurl;
	@ApiModelProperty(value = "请求方式")
	private String requesttype;
	@ApiModelProperty(value = "调用url")
	private String callurl;
	@ApiModelProperty(value = "serverURL")
	private String serverurl;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassurl() {
		return classurl;
	}

	public void setClassurl(String classurl) {
		this.classurl = classurl;
	}

	public String getClassdesc() {
		return classdesc;
	}

	public void setClassdesc(String classdesc) {
		this.classdesc = classdesc;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public String getMethoddesc() {
		return methoddesc;
	}

	public void setMethoddesc(String methoddesc) {
		this.methoddesc = methoddesc;
	}

	public String getMethodurl() {
		return methodurl;
	}

	public void setMethodurl(String methodurl) {
		this.methodurl = methodurl;
	}

	public String getRequesttype() {
		return requesttype;
	}

	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}

	public String getCallurl() {
		return callurl;
	}

	public void setCallurl(String callurl) {
		this.callurl = callurl;
	}

	public String getServerurl() {
		return serverurl;
	}

	public void setServerurl(String serverurl) {
		this.serverurl = serverurl;
	}

}
