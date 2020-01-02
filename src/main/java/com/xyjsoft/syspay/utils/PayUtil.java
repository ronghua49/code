/** 
 * 项目名称:QMHZ 
 * 文件名称:PayUtil.java 
 * 包名称:xyj.soft.pay.impl 
 * 文件日期:2018年4月24日下午2:39:22 
 * Copyright (c) 2018, 河南讯宜捷科技有限公司 All Rights Reserved. 
 * 
 */  
package com.xyjsoft.syspay.utils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/** 
 * 类名: PayUtil <br/> 
 * 类描述: 支付宝基础配置. <br/> 
 * date: 2018年4月24日 下午2:39:22 <br/> 
 * 
 * @author YuanHao 
 * @version 1.0
 * @since JDK 1.7 
 */
public class PayUtil {
	
	//支付宝网关名
	public static final String open_api_zf = "https://openapi.alipay.com/gateway.do";
	public static final String open_api_domain = "https://openapi.alipaydev.com/gateway.do";
	public static final String mcloud_api_domain = "http://mcloudmonitor.com/gateway.do";
	//# 签名类型: RSA->SHA1withRsa,RSA2->SHA256withRsa
	public static final	String sign_type = "RSA2";
	//accesstoken_url
	public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	public static final String URL_Oauth2Token = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String URL_SNSUserInfo = "https://api.weixin.qq.com/sns/userinfo";
	public static final String URL_UserInfo = "https://api.weixin.qq.com/cgi-bin/user/info";
	//微信公众号预支付url
	public static final String URL_PREPAYMENT = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信企业支付
	public static final String URL_QYZFURL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	//微信退款URL
	public static final String URL_WXTK = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//阿里退款URL
	public static final String URL_ALTK = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//微信关闭订单
	public static final String URL_WXCLOSE = "https://api.mch.weixin.qq.com/pay/closeorder";
	
	//微信XML解析
	public static Map<String, String> xmlToMap(String xml, String charset)
			throws UnsupportedEncodingException, DocumentException {

		Map<String, String> respMap = new HashMap<String, String>();

		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(xml.getBytes(charset)));
		Element root = doc.getRootElement();
		xmlToMap(root, respMap);
		return respMap;
	}
	//微信XML解析
	public static Map<String, String> xmlToMap(Element tmpElement, Map<String, String> respMap) {

		if (tmpElement.isTextOnly()) {
			respMap.put(tmpElement.getName(), tmpElement.getText());
			return respMap;
		}

		@SuppressWarnings("unchecked")
		Iterator<Element> eItor = tmpElement.elementIterator();
		while (eItor.hasNext()) {
			Element element = eItor.next();
			xmlToMap(element, respMap);
		}
		return respMap;
	}
	
}
