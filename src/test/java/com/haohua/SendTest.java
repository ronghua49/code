package com.haohua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 模拟高并发
 */
public class SendTest {


    public static String sendGet(String url, String param) throws Exception {
        String result = "";
        BufferedReader in = null;
        try{
            String charset = java.nio.charset.StandardCharsets.UTF_8.name();
            //String query = String.format("param1=%s",URLEncoder.encode(param1, charset));
            //若是两个请求参数，使用param1=%s¶m2=%s
            //String query = String.format("wd=%s¶m2=%s",URLEncoder.encode(param1, charset),URLEncoder.encode(param2, charset));
            String request = url + "?" + param;

            //打开和URL之间的连接
            URLConnection connection = new URL(request).openConnection();
            /* begin获取响应码 */
            HttpURLConnection httpUrlConnection = (HttpURLConnection)connection;
            httpUrlConnection.setConnectTimeout(300000);
            httpUrlConnection.setReadTimeout(300000);
            httpUrlConnection.connect();
            //获取响应码 =200
            int resCode = httpUrlConnection.getResponseCode();
            //获取响应消息 =OK
            String message = httpUrlConnection.getResponseMessage();

//            System.out.println("getResponseCode resCode ="+ resCode);
//            System.out.println("getResponseMessage message ="+ message);
            /* end获取响应码 */

            /* begin获取响应headers*/
//            System.out.println("响应头：" + result);
//            for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
//                System.out.println(header.getKey() + "=" + header.getValue());
//            }
            /* end获取响应headers*/

            /* begin获取响应内容 */
//            if (resCode == httpUrlConnection.getResponseCode()) {
//                int contentLength = httpUrlConnection.getContentLength();
//                System.out.println("contentLength--->" + contentLength);
//                if(contentLength > 0){
//                    in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null)
//                        result += "\n" + inputLine;
//                    System.out.println("响应内容：" + result);
//                }
//            }
            /* end获取响应内容 */

            /*
            //设置通用的请求属性
            connection.setRequestProperty("Accept-Charset", charset);
            //建立实际的连接
            connection.connect();
            //获取响应头部
            Map<String,List<String>> map = connection.getHeaderFields();
            System.out.println("\n显示响应Header信息...\n");
            //遍历所有的响应头字段并输出
            //方式一、
            for (String key : map.keySet()) {
                System.out.println(key + " : " + map.get(key));
            }
            //方式二、
            for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
                System.out.println(header.getKey() + "=" + header.getValue());
            }
            */
            //打印response body
            //方式一、定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                result += "\n" + inputLine;
            System.out.println("result===" + result);
            /*
            //方式二、使用Scanner
            System.out.println("响应内容：");
            InputStream response = connection.getInputStream();

            try(Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
            }*/

            //解析响应json
//            Gson gson = new Gson();
//            String s = gson.toJson(result);
//            System.out.println(s);
        }catch (Exception e){
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }// 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}


