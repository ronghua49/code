package proxy;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import com.haohua.Proxy.SaleProxy;
import com.haohua.Sale;
import com.haohua.service.Apple;
import com.haohua.service.Huawei;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class JdkDynamicProxyText {
@Test
    public void  AppleProxyText(){
    //代理苹果的销售代理
        Apple apple = new Apple();
        SaleProxy proxy = new SaleProxy(apple);
        Sale sale = (Sale) Proxy.newProxyInstance(apple.getClass().getClassLoader(),apple.getClass().getInterfaces(),proxy);
        sale.sale();
    }
    @Test
    public void HuaweiProxyTest(){
    //华为的销售代理
        Huawei huawei = new Huawei();
        SaleProxy saleProxy = new SaleProxy(huawei);
        Sale sale = (Sale) Proxy.newProxyInstance(huawei.getClass().getClassLoader(),huawei.getClass().getInterfaces(),saleProxy);
        sale.sale();
    }
}
