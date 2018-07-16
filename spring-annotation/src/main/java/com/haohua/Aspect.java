package com.haohua;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
    @Pointcut("execution(* com.haohua..*.*(..))")
    public void cutPoint(){}

    @Before("cutPoint()")
    public void beforeAdvice(JoinPoint joinPoint){
        Object object = joinPoint.getTarget();
        Object[] objects = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(object);
        System.out.println(methodName);
        System.out.println("前置通知");
    }
    @After("cutPoint()")
    public void  finalAdvice(){
        System.out.println("finally execute....");
    }

    @AfterReturning(value = "cutPoint()", returning="object")
    public void afterAdvice(Object object){
        //自动获得方法的返回值
        System.out.println("After execute.... object"+object);
    }
@AfterThrowing(value = "cutPoint()" ,throwing="ex")
    public void exceptionAdvice(Exception ex){
        //自动获取异常对象
        System.out.println("exception occured..");
        System.out.println(ex.getMessage());
    }
@Around("cutPoint()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object object=null;
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        try {
            object=  joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println( throwable.getMessage());
        }finally {
            System.out.println("finally execute.....");
        }
        return  object;
    }
}
