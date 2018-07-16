import com.libang.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author libang
 * @date 2018/7/16 17:54
 */
public class TestService {

    @Test
    public void testService(){
        ApplicationContext context = new ClassPathXmlApplicationContext("properties.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.save();

    }



}
