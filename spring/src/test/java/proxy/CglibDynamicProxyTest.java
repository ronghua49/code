package proxy;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import com.haohua.service.Apple;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

public class CglibDynamicProxyTest {

    Enhancer enhancer = new Enhancer();
    @Test
    public void cglibTest(){
        enhancer.setSuperclass(Apple.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("以旧换新，7折优惠");
                //传入目标对象和当前方法的参数来生成 这个方法的代理对象
                Object object = methodProxy.invokeSuper(o,objects);
                System.out.println("贴膜10元");
                return object;
            }
        });
        Apple appleProxy = (Apple) enhancer.create();
        appleProxy.sale();
    }
}
