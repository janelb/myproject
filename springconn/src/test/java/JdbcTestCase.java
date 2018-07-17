import com.libang.dao.StudentDao;
import com.libang.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/17 12:17
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:properties.xml")*/
public class JdbcTestCase extends BaseTestCase{


    @Autowired
    private StudentDao studentDao;

    @Test
    public void testSave(){
        Student student = new Student();
        student.setStuName("azsx");
        student.setSchoolId(2);
        studentDao.save(student);
    }



    @Test
    public void testFindById(){

        Student student = studentDao.findById(13);
        System.out.println(student.getStuName());
        Assert.assertNotNull(student);

    }


    @Test
    public void testFindAll(){

        List<Student> studentList = studentDao.findAll();
       for(Student student : studentList){
           System.out.println(student);
       }
    }


    @Test
    public void testCount(){
        int count = studentDao.count();
        System.out.println(count);
        Assert.assertEquals(13,count);

    }

    @Test
    public void testMap(){

        List<Map<String ,Object>> mapList = studentDao.findMapList();
        for(Map<String,Object> maps : mapList){
            for(Map.Entry<String ,Object> entry : maps.entrySet()){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());

            }

        }


    }
}
