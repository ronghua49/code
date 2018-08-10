import com.haohua.BaseContext;
import com.haohua.dao.UserDao;
import com.haohua.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//自动加载xml配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class UserDaoAnnotationText extends BaseContext {
    //ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

   @Autowired
  private  UserService userService;
   /*@Test
    public void AnnotationTest(){
        userDao.save(10);
    }*/
    @Test
    public void serviceAnnotation(){
        userService.getUserDao().delete();
    }
}
