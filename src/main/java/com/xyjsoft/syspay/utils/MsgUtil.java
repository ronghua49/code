package com.xyjsoft.syspay.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import springfox.documentation.spring.web.json.Json;

public class MsgUtil
{
	private static Map msgMap=new HashMap();
	static{
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("40001", "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口 ");
		msgMap.put("45009", "接口调用超过限制");
		msgMap.put("48001", "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限");
		/*msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");
		msgMap.put("-1", "系统繁忙，此时请开发者稍候再试");*/
	}
	public static String getMsgBycode(String code){
		if(msgMap.containsKey(code)){
			return "错误代号："+code+";"+msgMap.get(code).toString();
		}else{
			return "未知错误；错误代码为"+code;
		}
	}
	/**将xml格式字符串转为Map*/
	public static Map<String, String> parseXml(String xml) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		InputStream tInputStringStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
		SAXReader reader = new SAXReader();
		org.dom4j.Document document =  reader.read(tInputStringStream);
		
		Element root = document.getRootElement();
		
		List<Element> elementList = root.elements();

		
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		
		tInputStringStream.close();
		tInputStringStream = null;
		return map;
	}
	/**将xml格式字符串转为指定对象*/
	public static Object getObjectFromXML(String xml, Class tClass) throws Exception {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream(new DomDriver());
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }
	 public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException{
	        if(in == null)
	            return "";
	        
	        StringBuffer out = new StringBuffer();
	        byte[] b = new byte[4096];
	        for (int n; (n = in.read(b)) != -1;) {
	            out.append(new String(b, 0, n, "UTF-8"));
	        }
	        return out.toString();
	    }
	 
	 /**
	     * 发送客服消息
	     *
	     * @param accessToken
	     * @param message
	     * @return
	     * @throws Exception
	     */
	    private static String sendMsg(String accessToken, Map<String, Object> message) throws Exception {
	    	String jsonString = JSON.toJSONString(message);
	        String result = HttpUtil.sendHttpsPOST(
	        		"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=".concat(accessToken),jsonString);
	        return result;
	    }

	    /**
	     * 发送文本客服消息
	     *
	     * @param openId
	     * @param text
	     * @throws Exception
	     */
	    public static String sendText(String accessToken, String openId, String text) throws Exception {
	        Map<String, Object> json = new HashMap<String, Object>();
	        Map<String, Object> textObj = new HashMap<String, Object>();
	        textObj.put("content", text);
	        json.put("touser", openId);
	        json.put("msgtype", "text");
	        json.put("text", textObj);
	        String result = sendMsg(accessToken, json);
	        return result;
	    }
	    /**
	     * 发送模板消息
	     *
	     * @param openId
	     * @param text
	     * @throws Exception
	     */
	    public static String sendTmMsg(String accessToken,String content){
	    	String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	    	url+=accessToken;
	        String result = HttpUtil.sendHttpsPOST(url, content);
	        return result;
	    }
	    /**
	     * 获取模板信息列表
	     */
	    public static Map getMoban(String accessToken) throws Exception {
	    	String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=";
	    	url+=accessToken;
	        String result = HttpUtil.sendHttpsGET(url);
	        Map jsona = MsgUtil.parseXml(result);
	        return jsona;
	    }
	    
	    
}
