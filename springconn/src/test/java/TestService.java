import com.libang.Application;
import com.libang.dao.UserDao;
import com.libang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author libang
 * @date 2018/7/16 17:54
 */

//第一种：基于Di的Spring单元测试类进行注解方式来开启容器，加载bean
/*@RunWith(SpringJUnit4ClassRunner.class) //在junit运行时加载SpringJunit模块
*//*@ContextConfiguration(locations = "classpath:properties.xml")*//*//从classpath路径下读取spring配置文件，并创建spring容器

//第二种方法：配置类替换配置文件，配置文件的路径在com.libang下，
@ContextConfiguration(classes = Application.class)*///基于配置类的详情类来加载配置文件开启容器
public class TestService extends BaseTestCase {

    //直接注入对应的Bean
    @Autowired
    private UserService userService;

    @Test
    public void testService(){

        //基于配置类进行开启容器获取bean
       /* ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
       UserService userService = (UserService)context.getBean("userService");*/

        //
      /*  ApplicationContext context = new ClassPathXmlApplicationContext("properties.xml");
        UserService userService = (UserService) context.getBean("userService");*/


        userService.save();

    }



}
