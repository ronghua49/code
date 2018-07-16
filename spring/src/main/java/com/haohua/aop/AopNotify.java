package com.haohua.aop;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AopNotify {

    public void beforeAdvice(JoinPoint joinPoint) {
            //获得代理的当前方法名
          String methodName = joinPoint.getSignature().getName();
          //获得方法传入的参数列表
          Object[] objs = joinPoint.getArgs();
          //获得当前代理类的目标对象的名字
          Object object = joinPoint.getTarget();

          System.out.println(methodName);

          if (objs!=null){
              for(Object obj:objs){
                  System.out.println(obj);
              }
          }
          System.out.println(object.getClass().getName());
          System.out.println("前置通知");
    }
    public void afterAdvice() {
        System.out.println("后置通知");
    }
    public void exceptionAdvice() {
        System.out.println("异常通知");
    }
    public void finallyAdvice() {
        System.out.println("最终通知");
    }
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object obj=null;
        try {
            System.out.println("执行前----");
           obj= joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("抛异常了-----");
        }finally {
            System.out.println("最终执行了-----");
        }
        return  obj;
    }
}
