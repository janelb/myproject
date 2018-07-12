import com.libang.entity.Student;
import com.libang.entity.StudentExample;
import com.libang.mapper.StudentMapper;
import com.libang.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/12 17:58
 */
public class StudentMapperTestCase {
    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    Logger logger  = LoggerFactory.getLogger(StudentMapperTestCase.class);

    @Before
    public void before(){
        sqlSession = MybatisUtils.getSqlSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }


    /*根据id进行查询*/
    @Test
    public void testFindById(){
        Student student = studentMapper.selectByPrimaryKey(11);
        System.out.println(student);
    }

    /*模糊查询*/
    @Test
    public void testFindByKeys(){
        StudentExample studentExample = new StudentExample();
                studentExample.createCriteria().andStuNameLike("%张%");
        List<Student> studentList =studentMapper.selectByExample(studentExample);
        for(Student student : studentList){
            System.out.println(student);
        }
    }

    /*新增*/
    @Test
    public void  testAddStudent(){
        Student student = new Student();
        student.setStuName("赵大山");
        student.setSchoolId(2);
        int res = studentMapper.insert(student);
        sqlSession.commit();
        System.out.println("影响行数:" + res);

        int code = student.getId();
        System.out.println("id为：" + code);


    }


}
