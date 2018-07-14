import com.libang.dao.UserDao;
import com.libang.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author libang
 * @date 2018/7/14 10:51
 */
public class IocTestCase {

    @Test
    public void testIoC(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) context.getBean("userService");
        userService.save();
    }




}
