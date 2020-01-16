package com.haohua.prictice.aspect;

import com.alibaba.fastjson.JSONObject;
import com.haohua.prictice.SysLog;
import com.haohua.prictice.service.SysLogService;
import com.haohua.prictice.utils.HttpUtils;
import com.haohua.prictice.utils.IPUtils;
import com.haohua.prictice.utils.JwtTokenUtils;
import com.haohua.prictice.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类，记录日志
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired(required=false)
	private SysLogService sysLogServiceImpl;
    /**
     * 
     * @describe 检测controller层方法，
     * @author sjg 
     * @date 2019年8月3日 上午10:45:31
     * @version 1.0
     * @param point
     * @return
     * @throws Throwable
     */
	@Around("execution(* *..*.controller.*.*(..)) && @annotation(io.swagger.annotations.ApiOperation)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		String errorMessage = null;
		Exception error = null;
		long beginTime = System.currentTimeMillis();
		// 执行方法
		try {
			result = point.proceed();	
		}catch(Exception e) {
			StringWriter sw = new StringWriter(); 
            e.printStackTrace(new PrintWriter(sw, true)); 
            errorMessage = sw.toString();
			error = e;
		}
		
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		try {
			saveSysLog(point, time, errorMessage);	
		}catch(Exception e) {
			
		}
		
		if(errorMessage != null) {
			throw error;
		}
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time, String errorMessage) {
		String userName = SecurityUtils.getUsername();
		if(userName == null) {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if (attributes != null) {
				HttpServletRequest request = attributes.getRequest();
				String token = request.getHeader("token");	
				if (token != null && token.trim().length() > 0) {
					userName = getUserName(token);
				}else {
					token = request.getHeader("Authorization");	
					if(token != null && token.trim().length() > 0) {
						userName = getUserName(token);
					}
				}
			}
		}
		
/*		if(!(joinPoint.getTarget() instanceof CurdService)) {
			return ;
		}*/
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		SysLog sysLog = new SysLog();
		
		try {
			Method method = signature.getMethod();
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			if(apiOperation != null){
				//注解上的描述
				if(apiOperation.value() == null || "".equals(apiOperation.value())){
					sysLog.setOperation(apiOperation.notes());
				}else{
					sysLog.setOperation(apiOperation.value());
				}
			}
		}catch(Exception e) {
		}
		
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = JSONObject.toJSONString(args[0]);
			if(params.length() > 4990) {
				params = params.substring(0, 4990) + "...";
			}
			sysLog.setParams(params);
		} catch (Exception e){
		}

		// 获取request
		HttpServletRequest request = HttpUtils.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		// 用户名
		sysLog.setUserName(userName);
		
		// 执行时长(毫秒)
		sysLog.setTime(time);
		
		if(errorMessage != null) {
			if(errorMessage.length() > 4990) {
				errorMessage = errorMessage.substring(0, 4990) + "...";
			}
			sysLog.setErrorMessage(errorMessage);
		} 
		
		// 保存系统日志		
		sysLogServiceImpl.save(sysLog);	
	}
	
	private String getUserName(String token) {		
		return JwtTokenUtils.getUsernameFromToken(token);
	}
}
