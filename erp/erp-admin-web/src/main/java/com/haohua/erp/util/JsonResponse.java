package com.haohua.erp.util;    /*
 * @author  Administrator
 * @date 2018/7/27
 */

/**
 * 返回页面的json数据 格式
 */
public class JsonResponse {
    private static final  String STATE_SUCCESS="success";
    private static final  String STATE_ERROR="error";
    private String message;
    private Object result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static JsonResponse success(){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(STATE_SUCCESS);
        return jsonResponse;
    }

    public static JsonResponse success(Object object){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(STATE_SUCCESS);
        jsonResponse.setResult(object);
        return jsonResponse;
    }

    public static JsonResponse error(){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(STATE_ERROR);
        return jsonResponse;
    }

    public static JsonResponse error(Object object){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage(STATE_ERROR);
        jsonResponse.setResult(object);
        return jsonResponse;
    }

}

