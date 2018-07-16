package com.haohua.Proxy;    /*
 * @author  Administrator
 * @date 2018/7/16
 * 代理类的模板
 */

import com.haohua.Sale;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SaleProxy implements InvocationHandler {

   private  Sale sale ;
    public SaleProxy(Sale sale){
        this.sale = sale;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售之前，每部手机加价格500元");
        //根据详情对象和参数，获得此类的方法的代理对象
        Sale currProxy = (Sale) method.invoke(sale,args);
        System.out.println("销售后计算实际盈利");
        //返回代理对象
        return currProxy;
    }
}
