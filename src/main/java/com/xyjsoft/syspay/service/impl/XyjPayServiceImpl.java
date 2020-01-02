package com.xyjsoft.syspay.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayTradePayResponse;
import com.xyjsoft.core.util.MD5Util;
import com.xyjsoft.core.util.MathUtil;
import com.xyjsoft.syspay.dao.SysPayRecordMapper;
import com.xyjsoft.syspay.feign.PayOrderFeignService;
import com.xyjsoft.syspay.model.SysPayRecord;
import com.xyjsoft.syspay.service.SysPayRecordService;
import com.xyjsoft.syspay.service.XyjPayService;


/**
 * ---------------------------
 * 支付宝微信支付设置表 (SysPayServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class XyjPayServiceImpl implements XyjPayService {

	@Autowired
	private SysPayRecordService sysPayRecordService;
	@Autowired
	private SysPayRecordMapper sysPayRecordMapper;	
	@Autowired
	private PayOrderFeignService payOrderFeignService;
	@Override
	public void rejectMethodZFB(Map<String, String> params) {
		SysPayRecord xyjPayRecordByCode = sysPayRecordService.findByCode(params.get("out_trade_no").toString());
		
		if (xyjPayRecordByCode != null && "0".equals(xyjPayRecordByCode.getIsdispose()) && "0".equals(xyjPayRecordByCode.getCodestate())) {
			//单位：元
			Double total_amount = Double.parseDouble(params.get("total_amount").toString());
			Long total_amount_fen = (new Double(MathUtil.mul(total_amount, 100D))).longValue();
			if(!total_amount_fen.equals(xyjPayRecordByCode.getCzprice())) {
				//支付金额与下单金额不一致
				xyjPayRecordByCode.setMemo("支付金额与下单金额不一致,支付金额为:" + params.get("total_amount").toString());
				sysPayRecordMapper.update(xyjPayRecordByCode);
				return;
			}
			String status = params.get("trade_status").toString();
			
			//更新记录
			xyjPayRecordByCode.setCodestate("1"); 
			xyjPayRecordByCode.setIsdispose("1");
			
			String notify_time = params.get("notify_time").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				xyjPayRecordByCode.setFkdate(sdf.parse(notify_time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String trade_no = params.get("trade_no").toString();
			xyjPayRecordByCode.setThirdcode(trade_no);
			//调用处理接口
			boolean lostPort = lostPort(xyjPayRecordByCode.getPrepayid(),"0");
			if(!lostPort) {
				xyjPayRecordByCode.setMemo("支付成功,但业务处理失败");
				sysPayRecordMapper.update(xyjPayRecordByCode);
			}else{
				xyjPayRecordByCode.setMemo("支付成功并处理成功");
				sysPayRecordMapper.update(xyjPayRecordByCode);
			}
		}
	}
	
	@Override
	public void rejectMethodWX(Map jsona) {
		String prepayId = jsona.get("out_trade_no").toString();
		SysPayRecord xyjPayRecordByCode = sysPayRecordService.findByCode(prepayId);
		if (xyjPayRecordByCode != null && "0".equals(xyjPayRecordByCode.getIsdispose()) && "0".equals(xyjPayRecordByCode.getCodestate())) {
			//单位：分
			Long total_fee = Long.parseLong(jsona.get("total_fee").toString());
			if (!xyjPayRecordByCode.getCzprice().equals(total_fee)) {
				xyjPayRecordByCode.setMemo("支付金额与下单金额不一致,微信返回支付金额" + total_fee + ",应收金额" + xyjPayRecordByCode.getCzprice());
				sysPayRecordMapper.update(xyjPayRecordByCode);
				return;
			}
			
			//更新记录
			xyjPayRecordByCode.setCodestate("1"); 
			xyjPayRecordByCode.setIsdispose("1");
			
			String time_end = jsona.get("time_end").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			try {
				xyjPayRecordByCode.setFkdate(sdf.parse(time_end));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String transaction_id = jsona.get("transaction_id").toString();
			xyjPayRecordByCode.setThirdcode(transaction_id);
			
			//没有处理过,进行业务处理
			boolean lostPort = lostPort(xyjPayRecordByCode.getPrepayid(),"0");
			if(!lostPort) {
				xyjPayRecordByCode.setMemo("支付成功,但业务处理失败");
				sysPayRecordMapper.update(xyjPayRecordByCode);
			}else{
				xyjPayRecordByCode.setMemo("支付成功并处理成功");
				sysPayRecordMapper.update(xyjPayRecordByCode);
			}
		}		
	}
	
	public boolean lostPort(String code,String type){
		//订单号以CZ开头
		if(code.startsWith("CZ")) {
			//调用钱对code进行加密
			String Sign = MD5Util.string2MD5(code + "xyjsoft");
			boolean payBySuccess = payOrderFeignService.payBySuccess(code,Sign,type);
			return payBySuccess;
		}else if(code.startsWith("JY")) {
			String Sign = MD5Util.string2MD5(code + "xyjsoft");
			boolean payBySuccess = payOrderFeignService.JYpayBySuccess(code,Sign,type);
			return payBySuccess;
		}else if(code.startsWith("CZ2")) {
			return false;
		}else if(code.startsWith("CZ3")) {
			return false;
		}else if(code.startsWith("CZ4")) {
			return false;
		}else if(code.startsWith("CZ5")) {
			return false;
		}else{
			return false;
		}
	}

	
	public void Trade_pay(SysPayRecord sysPayRecord,AlipayTradePayResponse response) {
		sysPayRecord.setCodestate("1"); 
    	sysPayRecord.setIsdispose("1");
    	sysPayRecord.setFkdate(response.getGmtPayment());
    	sysPayRecord.setThirdcode(response.getTradeNo());
    	sysPayRecord.setMemo("支付成功,但业务处理失败");
		sysPayRecordMapper.update(sysPayRecord);
	}

	@Override
	public void swiftpassTrade_pay(SysPayRecord sysPayRecord, Map<String, String> resultMap) {
		sysPayRecord.setCodestate("1"); 
    	sysPayRecord.setIsdispose("1");
    	String trade_type = resultMap.get("trade_type");
    	if("pay.weixin.micropay".equals(trade_type)) {
    		sysPayRecord.setPayment("5");//微信条码支付
    	}else if("pay.alipay.micropay".equals(trade_type)) {
    		sysPayRecord.setPayment("4");//支付宝条码支付
    	}
    	String time_end = resultMap.get("time_end");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	try {
			sysPayRecord.setFkdate(sdf.parse(time_end));
		} catch (ParseException e) {
			sysPayRecord.setFkdate(new Date());
		}
    	sysPayRecord.setThirdcode(resultMap.get("out_transaction_id"));
    	sysPayRecord.setMemo("支付成功");
		sysPayRecordMapper.update(sysPayRecord);
	}

}
