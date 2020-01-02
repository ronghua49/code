package com.xyjsoft.syspay.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sun.net.www.protocol.http.Handler;
import sun.net.www.protocol.http.HttpURLConnection;



/**
 * 这个Https协议工具类，采用HttpsURLConnection实现。
 * 提供get和post两种请求静态方法
 * 
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class HttpUtil {
	

	
	private static TrustManager myX509TrustManager = new X509TrustManager() {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 

		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 

		}

		public X509Certificate[] getAcceptedIssuers() { 
			return null;
		}

	};

	public static String sendHttpsPOST(String url, String data,String filepath) {
		String result = null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			OutputStream out = httpsConn.getOutputStream() ;
			 
			if (data != null&&!data.equals(""))
				out.write(data.getBytes("UTF-8")); 
			out.flush();
			out.close();
			InputStream in=httpsConn.getInputStream();
			File f = new File(filepath);
            FileOutputStream fos = new FileOutputStream(f);
            byte[] b = new byte[1024];
            int n=0;
            while((n=in.read(b))!=-1){
              fos.write(b,0,n);
            }
            fos.close();
            in.close();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	public static String sendHttpsPOST(String url, String data) {
		String result = null;

		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			OutputStream out = httpsConn.getOutputStream() ;
			 
			if (data != null&&!data.equals(""))
				out.write(data.getBytes("UTF-8")); 
			out.flush();
			out.close();

			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(),"UTF-8"));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	public static String fetchTmpFile(String url, String data,String basepath) {
		String result = null;
		try {
			File fileDir = new File(basepath);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL u = new URL(null,url,new Handler());
		   HttpURLConnection  conn = (HttpURLConnection) u.openConnection();
		   conn.setRequestMethod("POST");
		   conn.setDoInput(true);
		   conn.connect();
		   BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		   String file_name ="";
		   /*String content_disposition = conn.getHeaderField("content-disposition");
		   //微信服务器生成的文件名称
		  
		   String[] content_arr = content_disposition.split(";");
		   if(content_arr.length  == 2){
		    String tmp = content_arr[1];
		    int index = tmp.indexOf("\"");
		    file_name =tmp.substring(index+1, tmp.length()-1);
		   }*/
		   file_name = String.valueOf(System.currentTimeMillis())+".jpg";
		   //生成不同文件名称
		   String filepath = basepath+File.separator+file_name;
		   //FileUtil.writeStream(filepath, bis);
		   //生成不同文件名称
		   File file = new File(filepath);
		   BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		   byte[] buf = new byte[2048];
		   int length = bis.read(buf);
		   while(length != -1){
			    bos.write(buf, 0, length);
			    length = bis.read(buf);
		   }
		   bos.close();
		   bis.close();
		   return file_name;
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	public static String sendHttpsGET(String url) {
		String result = null;
		HttpsURLConnection httpsConn = null;
		BufferedReader in = null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			httpsConn = (HttpsURLConnection) requestUrl.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("GET");
//			httpsConn.setDoOutput(true);
			  

			// 获取输入流
			in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(),"UTF-8"));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
			httpsConn.disconnect();
		} catch (KeyManagementException e) {
			httpsConn.disconnect();
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			httpsConn.disconnect();
			e.printStackTrace();
		} catch (MalformedURLException e) {
			httpsConn.disconnect();
			e.printStackTrace();
		} catch (ProtocolException e) {
			httpsConn.disconnect();
			e.printStackTrace();
		} catch (IOException e) {
			httpsConn.disconnect();
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送get请求，注意参数不能为汉字
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String sendHttpGET(String url,Map<String,Object> param) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		if(param != null && !param.isEmpty()){
			sb.append("?");
        	for(Entry<String, Object> m : param.entrySet()){
        		 sb.append(m.getKey()).append("=").append(m.getValue()).append("&");
        	}
        	sb.deleteCharAt(sb.length()-1);
        }
        URL getUrl = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");
        if(connection.getResponseCode() == 200){
        	 // 断开连接
            connection.disconnect();
        	return "1";
        }
        // 断开连接
        connection.disconnect();
		return "0";
	}
    
    /*
     * 微信公众号提供长连接转短连接
     */
    public static String LongUrl2short(String accesstoken,String longurl){
    	 String shortURL = null;
         String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=";
         requestUrl=requestUrl+accesstoken;
         String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\""+longurl+"\"}";
         String  jsonObject = sendHttpsPOST(requestUrl,jsonMsg);
         Map m = new HashMap();
         JSONObject parse = JSON.parseObject(jsonObject);
         if (null != jsonObject) {
             try {
                 shortURL = parse.getString("short_url");
                 
             } catch (Exception e) {
                 int errorCode = parse.getInteger("errcode");
                 String errorMsg = parse.getString("errmsg");
             }
         }
  
         return shortURL;
    }
    /**sendHttpsPOST
     * @param url: 请求网址
     * @param keystorepath: 证书绝对路径地址
     * @param keystorepsw: 证书密钥
     * @param data: 请求数据
     * @return 返回数据*/
    public static String sendHttpsPOST(String url, String keystorepath, String keystorepsw, String data) throws Exception{
    	String result = null;
    	KeyStore keyStore  = KeyStore.getInstance("PKCS12");
    	FileInputStream instream = new FileInputStream(keystorepath);
    	try {
    		keyStore.load(instream, keystorepsw.toCharArray());
    	} finally {
    		instream.close();
    	}
    	// Trust own CA and all self-signed certs
    	SSLContext sslcontext = SSLContexts.custom()
    							.loadKeyMaterial(keyStore, keystorepsw.toCharArray())
    							.build();
    	// Allow TLSv1 protocol only
    	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
    			sslcontext,
    			new String[] { "TLSv1" },
    			null,
    			SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    	CloseableHttpClient httpclient = HttpClients.custom()
    			.setSSLSocketFactory(sslsf)
    			.build();
    	try {
    		HttpPost httppost = new HttpPost(url);
    		httppost.setEntity(new StringEntity(data));
    		CloseableHttpResponse response = httpclient.execute(httppost);
    		try {
    			HttpEntity entity = response.getEntity();

    			/*System.out.println("----------------------------------------");
    			System.out.println(response.getStatusLine());*/
    			if (entity != null) {
    				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
    				String temp = bufferedReader.readLine();
    				/* 连接成一个字符串 */
    				while (temp != null) {
    					if (result != null)
    						result += temp;
    					else
    						result = temp;
    					temp = bufferedReader.readLine();
    				}
    			}
    			EntityUtils.consume(entity);
    		} finally {
    			response.close();
    		}
    	} finally {
    		httpclient.close();
    	}
    	return result;
    }
}
