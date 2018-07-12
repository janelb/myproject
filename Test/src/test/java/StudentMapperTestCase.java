import com.libang.entity.Student;
import com.libang.entity.Type;
import com.libang.mapper.StudentMapper;
import com.libang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author libang
 * @date 2018/7/11 12:37
 */
public class StudentMapperTestCase {
    Logger logger = LoggerFactory.getLogger(StudentMapperTestCase.class);
    SqlSession sqlSession;
    StudentMapper studentMapper;
    @Before
    public void before(){

         sqlSession = MybatisUtils.getSqlSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();

    }


    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<Student> studentList = studentMapper.findAll();
        for (Student student : studentList ){

            logger.debug("学生为:{}",student);

        }
    }

    @Test
    public void testFindById(){
        Student student = studentMapper.findById(12);
        logger.debug("student:{}",student);
    }


    @Test
    public void testAddStudent(){
        Student student = new Student();
        student.setStuName("老张");
        student.setSchoolId(3);
        int code = studentMapper.addStudent(student);
        sqlSession.commit();
        logger.debug("受影响的行数:{}",code);

        int res = student.getId();
        logger.debug("id为:{}",res);
    }

    @Test
    public void testFindByKeys(){
        String stuName = "%张%";
        List<Student> studentList = studentMapper.findByKeys(stuName);
        for(Student student : studentList){
            logger.debug("学生:{}",student);
        }
    }


    @Test
    public void testUpdate(){

        Student student = studentMapper.findById(15);

        student.setStuName("王铁蛋");
        studentMapper.update(student);

        sqlSession.commit();
    }

    @Test
    public void testFindByPage(){
        Map<String ,Object> maps = new HashMap<>();

        String stuName = "%张%";
         maps.put("stuName" ,stuName);
         maps.put("schoolId",2);
        List<Student> studentList = studentMapper.findByPage(maps);
        for(Student student : studentList){

            logger.debug("学生信息为:{}",student);
        }
    }

    @Test
    public void testFindByParam(){
        String stuName ="%张%";
        Integer schoolId=2;
        List<Student> studentList = studentMapper.findByParam(stuName,schoolId);
        for(Student student1 : studentList){
            logger.debug("学生:{}",student1);
        }
    }


    @Test
    public void testFindPage(){
        List<Student> studentList = studentMapper.findPage(1,3);
        for(Student student : studentList){
            logger.debug("学生信息为:{}",student);
        }
    }

    @Test
    public void testFindByIds(){
        List<Integer> idList = Arrays.asList(12,14,16);
        List<Student> studentList = studentMapper.findByIds(idList);
        for(Student student : studentList){
            logger.debug("学生信息为:{}",student);
        }
    }

    @Test
    public void testFindByStudentId(){
        Student student = studentMapper.findByStudentId(12);
        logger.debug("学生为:{}",student);
    }

    @Test
    public void testFindTypeByStudentId(){
        Student student = studentMapper.findTypeByStudentId(11);
        logger.debug("学生类型:{}",student);

    }

    @Test
    public void testAddbatch(){

        Type type = new Type();
        type.setTypeName("aaa1");
        Type type1 = new Type();
        type1.setTypeName("bbbb");
        List<Type> typeList = Arrays.asList(type,type1);
        studentMapper.addBatch(typeList);

        sqlSession.commit();

    }




}
