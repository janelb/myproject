import com.libang.Application;
import com.libang.dao.UserDao;
import com.libang.entity.Student;
import com.libang.service.StudentService;
import com.libang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author libang
 * @date 2018/7/16 17:54
 */

/*@RunWith(SpringJUnit4ClassRunner.class) //在junit运行时加载SpringJunit模块
*//*@ContextConfiguration(locations = "classpath:properties.xml")*//*//从classpath路径下读取spring配置文件，并创建spring容器

//第二种方法：配置类替换配置文件，配置文件的路径在com.libang下，
@RunWith(SpringJUnit4ClassRunner.class) //在junit运行时加载SpringJunit模块
@ContextConfiguration(classes = Application.class)*///基于配置类的详情
//第一种：基于Di的Spring单元测试类进行注解方式来开启容器，加载bean类来加载配置文件开启容器
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

    /*spring事务*/
    @Autowired
    private StudentService studentService;
    @Test
    public void testBatchSave()throws Exception{

        Student student = new Student();
        student.setStuName("assdas");
        student.setSchoolId(3);


        Student student1 = new Student();
        student1.setStuName(null);
        student1.setSchoolId(2);
        List<Student> studentList = Arrays.asList(student, student1);
        studentService.batchSave(studentList);


    }



}

/**
 *
 /*@RunWith(SpringJUnit4ClassRunner.class) //在junit运行时加载SpringJunit模块
 @ContextConfiguration(classes = Application.class)*//*//基于配置类的详情
 public class TestService extends BaseTestCase {

    //直接注入对应的Bean
    @Autowired
    private UserService userService;

    @Test*/
//第二种方法：配置类替换配置文件，配置文件的路径在com.libang下，

/*    public void testService(){

        //基于配置类进行开启容器获取bean
       *//* ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
       UserService userService = (UserService)context.getBean("userService");*//*
        userService.save();
    }

}*/





