package com.haohua.exception;    /*
 * @author  Administrator
 * @date 2018/12/25
 */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ronghaohua
 */
@ControllerAdvice
public class ProExceptionHandler {
    @ExceptionHandler(LackOfGoodsException.class)
    @ResponseBody
    public String LackOfGoodsException(Exception e) {
        return "此商品已经售罄";
    }

    @ExceptionHandler(BusyException.class)
    @ResponseBody
    public String BusyException(Exception e) {
        return "当前人数过多请重试";
    }

}
