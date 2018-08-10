import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ShiroTest {
   private static  Logger logger = LoggerFactory.getLogger(ShiroTest.class);
    public static void main(String[] args) {

   Factory<SecurityManager> securityManagerFactorty = new IniSecurityManagerFactory("classpath:shiro.ini");
    SecurityManager securityManager = securityManagerFactorty.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //如过当前对象登录，获得当前对象（获得主体账号），未登录创建一个对象
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        //获得当前用户输入的用户名和密码 产生token对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","123123 ");
        //判断当前账号是否认证(login)
        System.out.println(subject.isAuthenticated());
        //校验用户的token
        try{
            subject.login(usernamePasswordToken);
            //获得认证后的用户名
            subject.getPrincipal();
            //判断当前账号是否拥有这个角色
            System.out.println(subject.hasRole("admin"));
            boolean[] booleans =subject.hasRoles(Arrays.asList("admin","user"));
            for (boolean b :booleans){
                System.out.println(b);
            }
            //不拥有抛异常
            subject.checkRole("admin");
            System.out.println(subject.hasAllRoles(Arrays.asList("admin","user")));
            //判断角色是否有这个权限
            System.out.println(subject.isPermitted("role:add"));
            boolean[] bp = subject.isPermitted("role:add","role:del");
            for(boolean b:bp){
                System.out.println(b);
            }
            subject.checkPermission("role:del");

            Session session = subject.getSession();
            session.setAttribute("username",subject.getPrincipal());
            System.out.println(session.getAttribute("username"));
        }catch (UnknownAccountException e){
            logger.info("用户名不存在");
        }catch(IncorrectCredentialsException e){
            logger.info("密码错误");
        }catch(LockedAccountException e){
            logger.info("账户被冻结");
        }catch (AuthenticationException e){
            logger.debug("认证异常");
        }






    }
}
