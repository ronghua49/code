package proxy;    /*
 * @author  Administrator
 * @date 2018/7/16
 */

import com.haohua.Sale;
import com.haohua.service.Apple;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTestCase {
    @Test
    public  void aopTest(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sale sale = (Sale) context.getBean("apple");
        //获得代理对象的名
        System.out.println(sale.getClass().getName());
        sale.sale();
        int p = sale.price(2100);
        System.out.println(p);
    }



}
