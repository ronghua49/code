import com.haohua.dao.UserDao;
import com.haohua.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoAnnotationText {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
   @Test
    public void AnnotationTest(){
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.save(10);
    }
    @Test
    public void serviceAnnotation(){
       UserService userService = (UserService) context.getBean("userService");
       UserDao userDao = userService.getUserDao();
       userDao.delete();
    }
}
