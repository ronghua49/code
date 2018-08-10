import com.haohua.BaseContext;
import com.haohua.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoSimpleAnnotationTest extends BaseContext {
    @Autowired
    private UserService userService;
@Test
    public void userServiceTest(){
        userService.getUserDao().delete();
    }



}
