package com.xyjsoft.syspay.model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * ---------------------------
 * 支付信息表 (SysPayRecord)         
 * ---------------------------
 * 作者：  原浩
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是自己写的
 * ---------------------------
 */
@ApiModel(value = "SysPayRecord",description = "支付信息表") 
public class SysPayRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	/** id */
	@ApiModelProperty(value="id")
	private Long id;
	/** 购买时间 */
	@ApiModelProperty(value="购买时间")
	private java.util.Date czdate;
	/** 购买金额（单位：分） */
	@ApiModelProperty(value="购买金额（单位：分）")
	private Long czprice;
	/** 订单号 */
	@ApiModelProperty(value="订单号")
	private String prepayid;
	/** 是否付款成功 */
	@ApiModelProperty(value="是否付款成功")
	private String codestate;
	/** 备注 */
	@ApiModelProperty(value="备注")
	private String memo;
	/** 订单是否处理 */
	@ApiModelProperty(value="订单是否处理")
	private String isdispose;
	/** 支付方式(0:支付宝APP支付,1:微信APP支付,2:支付宝H5支付,3:微信H5支付) */
	@ApiModelProperty(value="支付方式(0:支付宝APP支付,1:微信APP支付,2:支付宝H5支付,3:微信H5支付)")
	private String payment;
	/** 实际付款时间 */
	@ApiModelProperty(value="实际付款时间")
	private java.util.Date fkdate;
	/** 第三方支付交易号 */
	@ApiModelProperty(value="第三方支付交易号")
	private String thirdcode;
	/** 累计退款金额，单位分 */
	@ApiModelProperty(value="累计退款金额，单位分")
	private Long tkmoney;
	/** 最近一次退款日期 */
	@ApiModelProperty(value="最近一次退款日期")
	private java.util.Date lasttkdate;
	/** 业务类型 */
	@ApiModelProperty(value="业务类型")
	private String ywtype;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Date getCzdate() {
		return czdate;
	}

	public void setCzdate(java.util.Date czdate) {
		this.czdate = czdate;
	}

	public Long getCzprice() {
		return czprice;
	}

	public void setCzprice(Long czprice) {
		this.czprice = czprice;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getCodestate() {
		return codestate;
	}

	public void setCodestate(String codestate) {
		this.codestate = codestate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsdispose() {
		return isdispose;
	}

	public void setIsdispose(String isdispose) {
		this.isdispose = isdispose;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public java.util.Date getFkdate() {
		return fkdate;
	}

	public void setFkdate(java.util.Date fkdate) {
		this.fkdate = fkdate;
	}

	public String getThirdcode() {
		return thirdcode;
	}

	public void setThirdcode(String thirdcode) {
		this.thirdcode = thirdcode;
	}

	public Long getTkmoney() {
		return tkmoney;
	}

	public void setTkmoney(Long tkmoney) {
		this.tkmoney = tkmoney;
	}

	public java.util.Date getLasttkdate() {
		return lasttkdate;
	}

	public void setLasttkdate(java.util.Date lasttkdate) {
		this.lasttkdate = lasttkdate;
	}

	public String getYwtype() {
		return ywtype;
	}

	public void setYwtype(String ywtype) {
		this.ywtype = ywtype;
	}

}