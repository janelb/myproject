import com.libang.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author libang
 * @date 2018/7/13 17:28
 */
public class SpringTestCase {

    @Test
    public void testSpring(){

        /*接口指向实现类对象*/
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    /*    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");*/
       /* UserDao userDao = (UserDao) context.getBean("userDao");*/

        /*spring容器默认创建是单例*/
      /*  UserDao userDao1 = (UserDao) context.getBean("userDao");*/
       /* System.out.println(userDao == userDao1);*/

       /* userDao.save();*/

        /*关闭容器*/
        /*context.close();*/



    }




}
