package com.xyjsoft.syspay.vo;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * ---------------------------
 * 支付状态 (PayType)         
 * ---------------------------
 * 作者：  原浩
 * 时间：  2019-03-06 17:23:11
 * 说明：  我是自己写的
 * ---------------------------
 */
@ApiModel(value = "PayType",description = "支付状态") 
public class PayType implements Serializable{

	private static final long serialVersionUID = 1L;
	/** 支付状态  0:失败,1:成功 */
	@ApiModelProperty(value="支付状态  0:失败,1:成功")
	private int payType;
	/** 支付信息 */
	@ApiModelProperty(value="支付提示信息")
	private String payState;
	
	
	public PayType() {
		super();
	}
	
	public PayType(int payType, String payState) {
		super();
		this.payType = payType;
		this.payState = payState;
	}

	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}



}