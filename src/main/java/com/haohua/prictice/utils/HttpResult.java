package com.haohua.prictice.utils;

import io.swagger.annotations.ApiModelProperty;

/**
 * HTTP结果封装
 * @author gsh456
 * @date 2019-03-15 11:10
 */
public class HttpResult {
	@ApiModelProperty(value="状态码")
	private int code = 200;
	@ApiModelProperty(value="返回信息")
	private String msg;
	@ApiModelProperty(value="返回数据")
	private Object data;
	
	public static HttpResult error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static HttpResult error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static HttpResult error(int code, String msg) {
		HttpResult r = new HttpResult();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static HttpResult ok(String msg) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		return r;
	}
	
	public static HttpResult ok(Object data) {
		HttpResult r = new HttpResult();
		if(data instanceof HttpResult){
			HttpResult data1 = ((HttpResult) data);
			r.setData(data1.getData());
			r.setMsg(data1.getMsg());
			return r;
		}
		r.setData(data);
		return r;
	}
	
	public static HttpResult ok() {
		return new HttpResult();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
