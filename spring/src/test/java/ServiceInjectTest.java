import com.haohua.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class ServiceInjectTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void daoInjectTest(){
    UserService userService = (UserService) context.getBean("userService2");
        userService.fun2();
    }
    @Test
    public void propertiesInjectTest(){
        UserService userService3 = (UserService) context.getBean("userService3");
        System.out.println(userService3.getNum());
       List<String> list = userService3.getList();
        for (String string: list) {
            System.out.println(string);
        }
        Map<String,Object> map = userService3.getMap();
        for(Map.Entry<String,Object> entry : map.entrySet()){
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }
        Set<Double> set = userService3.getSet();
        for (Double doubleSet:set){
            System.out.println(doubleSet);
        }
        Properties properties = userService3.getProperties();
        Enumeration<Object> enumerations = properties.keys();
        while(enumerations.hasMoreElements()){
            Object key = enumerations.nextElement();
            Object value= properties.get(key);
            System.out.println(key+"-->"+value);
        }
    }
    @Test
    public void construstorInjectTest(){
        UserService userService4 = (UserService) context.getBean("userService4");
        Map<String,Object> map = userService4.getMap();
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }

    }
}
