import com.libang.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author libang
 * @date 2018/7/16 17:53
 */
public class TestDao {


    @Test
    public void testDao(){

        ApplicationContext context = new ClassPathXmlApplicationContext("properties.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.save();

    }
}
