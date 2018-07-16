import com.haohua.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextBeanTest {

@Test
    public  void find(){
    ApplicationContext context = new ClassPathXmlApplicationContext("src/applicationContext.xml");
    UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.insert();
    }


}
