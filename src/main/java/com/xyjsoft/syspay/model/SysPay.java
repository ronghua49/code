package com.xyjsoft.syspay.model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * ---------------------------
 * 支付宝微信支付设置表 (SysPay)         
 * ---------------------------
 * 作者：  原浩
 * 时间：  2019-03-06 17:23:11
 * 说明：  我是自己写的
 * ---------------------------
 */
@ApiModel(value = "SysPay",description = "支付宝微信支付设置表") 
public class SysPay implements Serializable{

	private static final long serialVersionUID = 1L;
	/** id */
	@ApiModelProperty(value="id")
	private Long id;
	/** 微信使用APPID */
	@ApiModelProperty(value="微信使用APPID")
	private String wxappid;
	/** 微信使用appSecret */
	@ApiModelProperty(value="微信使用appSecret")
	private String wxappsecret;
	/** 微信使用商户号 */
	@ApiModelProperty(value="微信使用商户号")
	private String mchid;
	/** 微信使用终端 */
	@ApiModelProperty(value="微信使用终端")
	private String ipspbillcreateip;
	/** 微信使用key */
	@ApiModelProperty(value="微信使用key")
	private String wxKpikey;
	/** 微信使用APP名称 */
	@ApiModelProperty(value="微信使用APP名称")
	private String appName;
	/** 支付宝PID */
	@ApiModelProperty(value="支付宝PID")
	private String zfbPid;
	/** 支付宝appid */
	@ApiModelProperty(value="支付宝appid")
	private String zfbAppid;
	/** 支付宝应用私钥且转PKCS8格式 */
	@ApiModelProperty(value="支付宝应用私钥且转PKCS8格式")
	private String privateKey;
	/** 支付宝应用公钥 */
	@ApiModelProperty(value="支付宝应用公钥")
	private String publicKey;
	/** 支付宝支付宝公钥 */
	@ApiModelProperty(value="支付宝支付宝公钥")
	private String alipayPublicKey;
	/** 回调地址 */
	@ApiModelProperty(value="回调地址 ")
	private String notifyUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getWxappsecret() {
		return wxappsecret;
	}

	public void setWxappsecret(String wxappsecret) {
		this.wxappsecret = wxappsecret;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getIpspbillcreateip() {
		return ipspbillcreateip;
	}

	public void setIpspbillcreateip(String ipspbillcreateip) {
		this.ipspbillcreateip = ipspbillcreateip;
	}

	public String getWxKpikey() {
		return wxKpikey;
	}

	public void setWxKpikey(String wxKpikey) {
		this.wxKpikey = wxKpikey;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getZfbPid() {
		return zfbPid;
	}

	public void setZfbPid(String zfbPid) {
		this.zfbPid = zfbPid;
	}

	public String getZfbAppid() {
		return zfbAppid;
	}

	public void setZfbAppid(String zfbAppid) {
		this.zfbAppid = zfbAppid;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}