package com.xyjsoft.syspay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.alipay.api.response.AlipayTradePayResponse;
import com.swiftpass.config.SwiftpassConfig;
import com.swiftpass.util.SignUtil;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.XmlUtils;
import com.xyjsoft.core.util.HttpUtils;
import com.xyjsoft.core.vo.AliTradepayMessage;
import com.xyjsoft.core.vo.PayMessage;
import com.xyjsoft.core.vo.SwiftpassTradepayMessage;
import com.xyjsoft.syspay.dao.SysPayRecordMapper;
import com.xyjsoft.syspay.model.SysPay;
import com.xyjsoft.syspay.model.SysPayRecord;
import com.xyjsoft.syspay.service.SysPayRecordService;
import com.xyjsoft.syspay.service.SysPayService;
import com.xyjsoft.syspay.service.XyjPayService;
import com.xyjsoft.syspay.utils.HttpUtil;
import com.xyjsoft.syspay.utils.MsgUtil;
import com.xyjsoft.syspay.utils.MySecurity;
import com.xyjsoft.syspay.utils.PayUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ---------------------------
 * 支付接口 (XyjPayController)         
 * ---------------------------
 * 作者：  原 浩
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("XyjPay")
@Api(tags="XyjPayController 支付接口")
public class XyjPayController {
	@Autowired
	private SysPayService sysPayService;
	@Autowired
	private XyjPayService xyjPayService;
	@Autowired
	private SysPayRecordService sysPayRecordService;
	@Autowired
	private SysPayRecordMapper sysPayRecordMapper;
    private static Logger logger = LoggerFactory.getLogger(XyjPayController.class);
	/**
	 * 阿里APP支付
	 * @exception 
	 * @since  1.0.0
	*/
	@PostMapping(value="/payResult")
	@ApiOperation(value="支付宝回调", httpMethod = "POST", notes = "支付宝APP支付")
	public void payResult() {
		HttpServletRequest request = HttpUtils.getHttpServletRequest();
		HttpServletResponse response = HttpUtils.getHttpServletResponse();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
		    }
			//乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		 }
		SysPay sysPay = sysPayService.get();
		boolean flag = false;
		try {
			flag = AlipaySignature.rsaCheckV1(params, sysPay.getAlipayPublicKey(), "utf-8", "RSA2");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		if(!flag){
			return;
		}else{
			//验证签成功,判断是否支付成功,并修改订单状态
			String code = params.get("out_trade_no");
			String status = params.get("trade_status");
			SysPayRecord findByCode = sysPayRecordService.findByCode(code);
			//支付成功
			try{
				if("TRADE_SUCCESS".equals(status)) {
					//支付成功
					xyjPayService.rejectMethodZFB(params);
					response.getWriter().print("success");
				}else if("WAIT_BUYER_PAY".equals(status)){
					//订单支付失败
					findByCode.setMemo("支付宝返回支付创建,等待买家付款");
					sysPayRecordMapper.update(findByCode);
					return;
				}else if("TRADE_CLOSED".equals(status)){
					//用户中途取消
					findByCode.setMemo("未付款交易超时关闭，或支付完成后全额退款");
					sysPayRecordMapper.update(findByCode);
					response.getWriter().print("success");
					return;
				}else if("TRADE_FINISHED".equals(status)){
					//网络连接出错
					findByCode.setMemo("交易结束，不可退款");
					sysPayRecordMapper.update(findByCode);
					response.getWriter().print("success");
					return;
				}
			}catch (Exception e) {
				findByCode.setMemo("订单回调处理异常");
				sysPayRecordMapper.update(findByCode);
			}
		}
	}
	
	/**
	 * 阿里APP支付
	 * @exception 
	 * @since  1.0.0
	*/
	@PostMapping(value="/payOrderByAliApp")
	@ApiOperation(value="支付宝APP支付", httpMethod = "POST", notes = "支付宝APP支付")
	public String payOrderByAliApp(@ApiParam(name="payMessage",value="支付信息对象") @RequestBody PayMessage payMessage) {
		SysPayRecord sysPayRecord = sysPayRecordService.findByCode(payMessage.getPayCode());
		if(sysPayRecord != null) {
			if(sysPayRecord.getCodestate().equals("1")) {
				throw new RuntimeException("订单已支付完成,请勿重复支付");
			}
		}else{
			sysPayRecord = new SysPayRecord();
		}
		SysPay sysPay = sysPayService.get();
		//按分计算金额
		sysPayRecord.setCzdate(new Date());
		sysPayRecord.setCzprice(payMessage.getPayPrice());
		sysPayRecord.setMemo("订单发起支付");
		sysPayRecord.setCodestate("0");
		sysPayRecord.setPrepayid(payMessage.getPayCode());
		sysPayRecord.setIsdispose("0");
		sysPayRecord.setPayment("0");
		sysPayRecord.setTkmoney(0L);
		if(payMessage.getYwType() == null || "".equals(payMessage.getYwType())){
			payMessage.setYwType("0");
		}
		sysPayRecord.setYwtype(payMessage.getYwType());
		sysPayRecordService.save(sysPayRecord);
		
		Long paymoney = payMessage.getPayPrice();
		if(paymoney <= 0){
			throw new RuntimeException("金额必须大于零");
		}
		String type = "APP支付宝支付";
		String jeS = String.valueOf((new BigDecimal((double)paymoney / 100)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		String desc = sysPay.getAppName()+ ":" + sysPayRecord.getPrepayid();
		try{
			//实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient(PayUtil.open_api_zf, sysPay.getZfbAppid(), sysPay.getPrivateKey(), "json", "utf-8",sysPay.getPublicKey(), PayUtil.sign_type);
			//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody(desc);
			model.setSubject(desc);
			model.setOutTradeNo(payMessage.getPayCode());
			/*设置未付款支付宝交易的超时时间，一旦超时，该笔交易就会自动被关闭。
			 * 当用户进入支付宝收银台页面（不包括登录页面），会触发即刻创建支付宝交易，此时开始计时。
			 * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 
			 * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。*/
			model.setTimeoutExpress("1c");
			/*设置支付金额，单位为元，精确到小数点后两位*/
			model.setTotalAmount(jeS);
			model.setProductCode("QUICK_MSECURITY_PAY");
			/*设置收款账户*/
			model.setSellerId(sysPay.getZfbPid());
			/*设置回传参数*/
			model.setPassbackParams(java.net.URLEncoder.encode(type, "UTF-8"));
			ExtendParams extendparams = new ExtendParams();
			/*设置服务商*/
			//extendparams.setSysServiceProviderId(XYJConfigs.getPid());
			model.setExtendParams(extendparams);
			request.setBizModel(model);
			request.setNotifyUrl(sysPay.getNotifyUrl());
	        //这里和普通的接口调用不同，使用的是sdkExecute
	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
	        if(response.isSuccess()){
	        	System.out.println(response.getBody());
	 	        //就是orderString 可以直接给客户端请求，无需再做处理。
	 	        Map mapR = new HashMap();
	 	        mapR.put("orderInfo", response.getBody());
	 	        mapR.put("type", type);
	 			mapR.put("out_trade_no", payMessage.getPayCode());
	 			String jsonString = JSON.toJSONString(mapR);
	 	        return jsonString;
	        }else{
	        	System.out.println(response.getBody());
	        	throw new RuntimeException("支付宝app支付接口调用失败");
	        }
		}catch(Exception e){
			throw new RuntimeException("支付宝app支付接口调用失败");
		}
	}
	
	/**
	 * 微信APP支付
	 * @exception 
	 * @since  1.0.0
	*/
	@PostMapping(value="/payOrderByWxApp")
	@ApiOperation(value="微信APP支付", httpMethod = "POST", notes = "微信APP支付")
	public Map payOrderByWxApp(@ApiParam(name="payMessage",value="支付信息对象") @RequestBody PayMessage payMessage) {
		SysPayRecord sysPayRecord = sysPayRecordService.findByCode(payMessage.getPayCode());
		
		if(sysPayRecord != null) {
			if(sysPayRecord.getCodestate().equals("1")) {
				throw new RuntimeException("订单已支付完成,请勿重复支付");
			}
		}else{
			sysPayRecord = new SysPayRecord();
		}
		
		SysPay sysPay = sysPayService.get();
		//按分计算金额
		sysPayRecord.setCzdate(new Date());
		sysPayRecord.setCzprice(payMessage.getPayPrice());
		sysPayRecord.setMemo("订单发起微信支付");
		sysPayRecord.setCodestate("0");
		sysPayRecord.setPrepayid(payMessage.getPayCode());
		sysPayRecord.setIsdispose("0");
		sysPayRecord.setPayment("0");
		sysPayRecord.setTkmoney(0L);
		if(payMessage.getYwType() == null || "".equals(payMessage.getYwType())){
			payMessage.setYwType("0");
		}
		sysPayRecord.setYwtype(payMessage.getYwType());
		sysPayRecordService.save(sysPayRecord);
		
		Long paymoney = sysPayRecord.getCzprice();
		if(paymoney == 0){
			throw new RuntimeException("金额必须大于零");
		}
		String type = "APP微信支付";
		String desc = sysPay.getAppName()+ ":" + sysPayRecord.getPrepayid();
		Double nonce_str = (Math.random() * 3);
		String signTemp = "appid=" + sysPay.getWxappid() 
				+ "&body="+ desc
				+ "&mch_id="+ sysPay.getMchid()
				+ "&nonce_str=" + nonce_str 
				+ "&notify_url=" + sysPay.getNotifyUrl()+"/WeixinPayResult" 
				+ "&out_trade_no=" + payMessage.getPayCode() 
				+ "&spbill_create_ip="+ payMessage.getUserIP()
				+ "&total_fee=" + paymoney
				+ "&trade_type=APP"
				+ "&key=" + sysPay.getWxKpikey(); // 这个key注意
		// 通过加密算法获取到sign
		String sign = new MySecurity().encode(signTemp, MySecurity.MD5).toUpperCase();
		String xml = "<xml version='1.0' encoding='UTF-8' standalone='yes'>" 
				+ "<appid><![CDATA["+ sysPay.getWxappid() + "]]></appid>" 
				+ "<body><![CDATA["+ desc +"]]></body>"
				+ "<mch_id><![CDATA["+ sysPay.getMchid() + "]]></mch_id>" 
				+ "<nonce_str><![CDATA[" + nonce_str + "]]></nonce_str>"
				+ "<notify_url><![CDATA[" + sysPay.getNotifyUrl()+"/WeixinPayResult" + "]]></notify_url>" 
				+ "<out_trade_no><![CDATA[" + payMessage.getPayCode()  + "]]></out_trade_no>"
				+ "<spbill_create_ip><![CDATA[" + payMessage.getUserIP() + "]]></spbill_create_ip>"
				+ "<total_fee><![CDATA[" + paymoney + "]]></total_fee>"
				+ "<trade_type><![CDATA[APP]]></trade_type>" 
				+ "<sign><![CDATA[" + sign + "]]></sign>" + "</xml>  ";
		String falseCode = HttpUtil.sendHttpsPOST(PayUtil.URL_PREPAYMENT, xml);
		Map<String, String> xmlToMap;
		try {
			xmlToMap = PayUtil.xmlToMap(falseCode, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} 
		if(xmlToMap.get("return_code").toString().equals("SUCCESS")
				&& xmlToMap.get("result_code").toString().equals("SUCCESS")){
			Map mapR = xmlToMap;
			Map  mapreq = new HashMap();
			mapreq.put("type", type);
			mapreq.put("out_trade_no", payMessage.getPayCode());
			mapreq.put("wxpaykey",sysPay.getWxKpikey() );
			String prepay_id = mapR.get("prepay_id").toString();
			//IOS签名
			Double nonce_str1 = (Math.random() * 3);
			Date date = new Date();
			String timestamp = String.valueOf(date.getTime() / 1000);
			mapreq.put("appid",sysPay.getWxappid());
			mapreq.put("partnerid",mapR.get("mch_id"));
			mapreq.put("prepayid",prepay_id);
			mapreq.put("package","Sign=WXPay");
			mapreq.put("noncestr",nonce_str1.toString());
			mapreq.put("timestamp",timestamp);
			
			mapreq.put("nonce_str",nonce_str);
			mapreq.put("timeStamp",timestamp);
			String APPsign = getWebSign(sysPay.getWxappid(),timestamp,nonce_str1,"prepay_id="+prepay_id,sysPay.getWxKpikey());
			mapreq.put("APPsign", APPsign);
			return mapreq;
		}else{
			if(xmlToMap.get("return_code").toString().equals("FAIL")){
				throw new RuntimeException("微信APP支付接口调用失败：" + xmlToMap.get("return_msg"));	
			}else if(xmlToMap.get("result_code").toString().equals("FAIL")){
				throw new RuntimeException("微信APP支付接口调用失败：" + xmlToMap.get("err_code_des"));
			}
			throw new RuntimeException("微信APP支付接口调用失败");
		}
	}
	/**
	 * <b>详细描述</b>:生成签名方法<br>
	 * @exception 
	 * @since  1.0.0
	 */
	public String getWebSign(String appid,String timeStamp,Double nonce_str,String Package,String key) {
		String signTemp = "appId=" + appid
		+ "&nonceStr=" + nonce_str 
		+ "&package=" + Package 
		+ "&signType=MD5"
		+ "&timeStamp=" + timeStamp 
		+ "&key=" + key; // 这个key注意
		String sign = new MySecurity().encode(signTemp.toString(), MySecurity.MD5).toUpperCase();
		return sign;
	}
	
	/**
	 * 阿里APP支付
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	*/
	@PostMapping(value="/payByWxResult")
	@ApiOperation(value="微信支付回调", httpMethod = "POST", notes = "微信支付回调")
	public void payByWxResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		ServletInputStream in = null;
		Map jsona = new HashMap();
		try {
			// 转换微信post过来的xml内容
			XStream xs = new XStream(new DomDriver());
			in = req.getInputStream();
			String xmlMsg = MsgUtil.inputStream2String(in);
			jsona = MsgUtil.parseXml(xmlMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String return_code = "";
		String return_msg = "";
		// 判断是否有返回值
		if (jsona.containsKey("return_code")) {
			// 有返回值
			String return_code1 = jsona.get("return_code").toString();
			// 判断通讯是否成功
			if ("SUCCESS".equals(return_code1)) {
				// 通讯成功
				String result_code = jsona.get("result_code").toString();
				// 获取订单号,查询并修改状态
				if("SUCCESS".equals(result_code)) {
					//成功
					xyjPayService.rejectMethodWX(jsona);
					return_code = return_code1;
					return_msg = "OK";
				}else{
					//失败
					return_code = return_code1;
					return_msg = jsona.get("return_msg").toString();
				}
			} else {
				// 通讯失败
				return_code = return_code1;
				return_msg = jsona.get("return_msg").toString();
			}
		} else {
//			 没有返回值
			return_code = "FAIL";
			return_msg = "没有参数";
		}
		String returnStr = "<xml version='1.0' encoding='UTF-8' standalone='yes'>" + "<return_code><![CDATA["
				+ return_code + "]]></return_code>" + "<return_msg><![CDATA[" + return_msg + "]]></return_msg>"
				+ "</xml> ";
//		 返回微信结果
		resp.getWriter().print(returnStr);
	}
	/**当面付*/
	@PostMapping(value="/Trade_pay")
	@ApiOperation(value="支付宝条码支付", httpMethod = "POST", notes = "条码支付")
	public String Trade_pay(@ApiParam(name="aliTradepayMessage",value="支付信息对象") @RequestBody AliTradepayMessage aliTradepayMessage) {
		SysPayRecord sysPayRecord = sysPayRecordService.findByCode(aliTradepayMessage.getPayCode());
		if(sysPayRecord != null) {
			if(sysPayRecord.getCodestate().equals("1")) {
				throw new RuntimeException("订单已支付完成,请勿重复支付");
			}
		}else{
			sysPayRecord = new SysPayRecord();
		}
		SysPay sysPay = sysPayService.get();
		//按分计算金额
		sysPayRecord.setCzdate(new Date());
		sysPayRecord.setCzprice(aliTradepayMessage.getTotalAmount());
		sysPayRecord.setMemo("订单发起支付");
		sysPayRecord.setCodestate("0");
		sysPayRecord.setPrepayid(aliTradepayMessage.getPayCode());
		sysPayRecord.setIsdispose("0");
		sysPayRecord.setPayment("0");
		sysPayRecord.setTkmoney(0L);
		if(aliTradepayMessage.getYwType() == null || "".equals(aliTradepayMessage.getYwType())){
			aliTradepayMessage.setYwType("0");
		}
		sysPayRecord.setYwtype(aliTradepayMessage.getYwType());
		sysPayRecordService.save(sysPayRecord);
		
		Long paymoney = aliTradepayMessage.getTotalAmount();
		if(paymoney <= 0){
			Map mapout = new HashMap(10);
        	mapout.put("isok", 0);
        	mapout.put("msg", "金额必须大于零");
        	mapout.put("tradecode", "");
 	        return JSONObject.toJSONString(mapout);
		}
		String type = "支付宝当面付";
		String jeS = String.valueOf((new BigDecimal((double)paymoney / 100)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		try{
			
			//实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient(PayUtil.open_api_zf, sysPay.getZfbAppid(), sysPay.getPrivateKey(), "json", "utf-8",sysPay.getPublicKey(), PayUtil.sign_type);
			//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradePayRequest request = new AlipayTradePayRequest();
			//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			AlipayTradePayModel model = new AlipayTradePayModel();
			model.setOutTradeNo(aliTradepayMessage.getPayCode());
			model.setScene("bar_code");
			model.setAuthCode(aliTradepayMessage.getAuthCode());
			model.setProductCode("FACE_TO_FACE_PAYMENT");
			model.setBody(aliTradepayMessage.getBody());
			model.setSubject(aliTradepayMessage.getSubject());
			
			/*设置未付款支付宝交易的超时时间，一旦超时，该笔交易就会自动被关闭。
			 * 当用户进入支付宝收银台页面（不包括登录页面），会触发即刻创建支付宝交易，此时开始计时。
			 * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 
			 * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。*/
			model.setTimeoutExpress("1c");
			/*设置支付金额，单位为元，精确到小数点后两位*/
			model.setTotalAmount(jeS);
			
			/*设置收款账户*/
			model.setSellerId(sysPay.getZfbPid());
			request.setBizModel(model);
			request.setNotifyUrl(sysPay.getNotifyUrl());
	        //这里和普通的接口调用不同，使用的是sdkExecute
	        AlipayTradePayResponse response = alipayClient.sdkExecute(request);
	        if(response.isSuccess()){
	        	xyjPayService.Trade_pay(sysPayRecord, response);
	        	Map mapout = new HashMap(10);
	        	mapout.put("isok", 1);
	        	mapout.put("msg", "支付宝当面付成功");
	        	mapout.put("tradecode", response.getTradeNo());
	 	        return JSONObject.toJSONString(mapout);
	        }else{
	        	Map mapout = new HashMap(10);
	        	mapout.put("isok", 0);
	        	mapout.put("msg", "支付宝条码支付接口调用失败");
	        	mapout.put("tradecode", "");
	 	        return JSONObject.toJSONString(mapout);
	        }
		}catch(Exception e){
			Map mapout = new HashMap(10);
        	mapout.put("isok", 0);
        	mapout.put("msg", "支付宝条码支付接口调用失败");
        	mapout.put("tradecode", "");
 	        return JSONObject.toJSONString(mapout);
		}
	}
	/**中信银行当面付
	 * @throws IOException */
	@PostMapping(value="/swiftpass_Trade_pay")
	@ApiOperation(value="支付宝条码支付", httpMethod = "POST", notes = "条码支付")
	public String swiftpass_Trade_pay(@ApiParam(name="SwiftpassTradepayMessage",value="支付信息对象") @RequestBody SwiftpassTradepayMessage swiftpassTradepayMessage) throws IOException {
		SysPayRecord sysPayRecord = sysPayRecordService.findByCode(swiftpassTradepayMessage.getPayCode());
		if(sysPayRecord != null) {
			if(sysPayRecord.getCodestate().equals("1")) {
				throw new RuntimeException("订单已支付完成,请勿重复支付");
			}
		}else{
			sysPayRecord = new SysPayRecord();
		}
		SysPay sysPay = sysPayService.get();
		//按分计算金额
		sysPayRecord.setCzdate(new Date());
		sysPayRecord.setCzprice(swiftpassTradepayMessage.getTotalAmount());
		sysPayRecord.setMemo("订单发起支付");
		sysPayRecord.setCodestate("0");
		sysPayRecord.setPrepayid(swiftpassTradepayMessage.getPayCode());
		sysPayRecord.setIsdispose("0");
		sysPayRecord.setPayment("0");
		sysPayRecord.setTkmoney(0L);
		if(swiftpassTradepayMessage.getYwType() == null || "".equals(swiftpassTradepayMessage.getYwType())){
			swiftpassTradepayMessage.setYwType("0");
		}
		sysPayRecord.setYwtype(swiftpassTradepayMessage.getYwType());
		sysPayRecordService.save(sysPayRecord);
		final SysPayRecord sysPayRecordT = sysPayRecord;
		Long paymoney = swiftpassTradepayMessage.getTotalAmount();
		SortedMap<String,String> map = new TreeMap<String,String>();
        
    	map.put("mch_id", SwiftpassConfig.mch_id);
    	map.put("service","unified.trade.micropay");
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        map.put("device_info","pos001");//终端设备号，商户自定义。特别说明：对于QQ钱包支付，此参数必传，否则会报错。
        map.put("sign_type","RSA_1_256");
        map.put("out_trade_no",swiftpassTradepayMessage.getPayCode());
        map.put("body", swiftpassTradepayMessage.getBody());
        map.put("total_fee", String.valueOf(swiftpassTradepayMessage.getTotalAmount()));
        map.put("mch_create_ip", "192.168.1.100");
        map.put("auth_code", swiftpassTradepayMessage.getAuthCode());
        Map<String,String> params = SignUtils.paraFilter(map);
        
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign_type = map.get("sign_type");
        
        map.put("sign", SignUtil.getSign(sign_type, preStr));
        
        logger.info("中信银行条码支付，请求xml："+XmlUtils.toXml(map));
        String reqUrl = SwiftpassConfig.req_url;
        logger.info("中信银行条码支付，reqUrl：" + reqUrl);
        
        logger.info("中信银行条码支付，reqParams:" + XmlUtils.parseXML(map));
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        String reSign = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
            	
                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                
                reSign = resultMap.get("sign");
                sign_type = resultMap.get("sign_type");
                res = XmlUtils.toXml(resultMap);
                if(resultMap.containsKey("sign")){
                	if(SignUtil.verifySign(reSign, sign_type, resultMap)){
                			logger.info("中信银行条码支付，返回参数验证签名通过。。。");
                			if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))
                					&& "0".equals(String.valueOf(resultMap.get("pay_result")))){
                				xyjPayService.swiftpassTrade_pay(sysPayRecord,resultMap);
                				Map mapout = new HashMap(10);
                	        	mapout.put("isok", 1);
                	        	mapout.put("msg", "中信银行当面付成功");
                	        	mapout.put("trade_type", resultMap.get("trade_type"));
                	        	mapout.put("tradecode", resultMap.get("out_transaction_id"));
                	 	        return JSONObject.toJSONString(mapout);
                            }else{
                            	if("Y".equals(resultMap.get("need_query"))){
                            		Map mapout = new HashMap(10);
                    	        	mapout.put("isok", 2);
                    	        	mapout.put("msg", "等待用户输入密码");
                    	        	mapout.put("trade_type", "");
                    	        	mapout.put("tradecode", "");
                    	        	logger.info("中信银行接口调用成功等待用户输入密码，返回原始报文："+res);
                    	        	new Thread(new Runnable() {
										@Override
										public void run() {
											 /*开查询线程*/
											boolean isok = false;
											int i = 0;
											while(!isok && i < 6){
												i++;
												if(searchPay(swiftpassTradepayMessage.getPayCode(),sysPayRecordT)){
													isok = true;
												}else{
													try {
														Thread.sleep(5000);
													} catch (InterruptedException e) {
														
													}
												}
											}
										}
									}).start();
                    	        	 
                    	 	        return JSONObject.toJSONString(mapout);
                            	}else if("N".equals(resultMap.get("need_query"))){
                            		Map mapout = new HashMap(10);
                    	        	mapout.put("isok", 0);
                    	        	mapout.put("msg", "中信银行当面付接口调用失败");
                    	        	mapout.put("trade_type", "");
                    	        	mapout.put("tradecode", "");
                    	        	logger.info("中信银行接口调用失败，返回原始报文："+res);
                    	 	        return JSONObject.toJSONString(mapout);
                            	}
                            }
                     }
                } 
            }else{
                res = "操作失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "系统异常";
        } finally {
            if(client != null){
                client.close();
            }
        }
        return "操作失败";
	}
	
	public boolean searchPay(String precode,SysPayRecord sysPayRecord){
		SortedMap<String,String> map = new TreeMap<String,String>();
        
    	map.put("mch_id", SwiftpassConfig.mch_id);
    	map.put("service","unified.trade.query");
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        map.put("sign_type","RSA_1_256");
        map.put("out_trade_no",precode);
        Map<String,String> params = SignUtils.paraFilter(map);
        
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign_type = map.get("sign_type");
        
        map.put("sign", SignUtil.getSign(sign_type, preStr));
        
        logger.info("中信银行条码支付查询，请求xml："+XmlUtils.toXml(map));
        String reqUrl = SwiftpassConfig.req_url;
        logger.info("中信银行条码支付查询，reqUrl：" + reqUrl);
        
        logger.info("中信银行条码支付查询，reqParams:" + XmlUtils.parseXML(map));
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        String reSign = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
            	
                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                
                reSign = resultMap.get("sign");
                sign_type = resultMap.get("sign_type");
                res = XmlUtils.toXml(resultMap);
                if(resultMap.containsKey("sign")){
                	if(SignUtil.verifySign(reSign, sign_type, resultMap)){
                			logger.info("中信银行条码支付，返回参数验证签名通过。。。");
                			if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))
                					&& "SUCCESS".equals(String.valueOf(resultMap.get("trade_state")))){
                				xyjPayService.swiftpassTrade_pay(sysPayRecord,resultMap);
                	 	        return true;
                            }else{
                            	return false;
                            }
                     }
                } 
            }else{
                res = "操作失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "系统异常";
        } finally {
            if(client != null){
                try {
					client.close();
				} catch (IOException e) {
					
				}
            }
        }
		return false;
	}
}
