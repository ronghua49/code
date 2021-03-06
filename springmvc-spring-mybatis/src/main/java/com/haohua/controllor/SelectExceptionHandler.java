package com.haohua.controllor;    /*
 * @author  Administrator
 * @date 2018/7/24
 */

import com.haohua.exception.DatasourceAccessException;
import com.haohua.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class SelectExceptionHandler {

    @ExceptionHandler({NotFoundException.class,DatasourceAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundException(){
        return "error/404";
    }


}
