package com.libang.test;

import com.libang.entity.Student;

import com.libang.mapper.StudentMapper;
import com.libang.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/10 10:52
 */
public class StudentMapperTestCase {

    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    Logger logger = LoggerFactory.getLogger(StudentMapperTestCase.class);

    @Before
    public void before(){
         sqlSession = MybatisUtils.getSqlSession();
        //动态代理获取接口的实现类对象
        studentMapper = sqlSession.getMapper(StudentMapper.class);

    }
    @After
    public void after(){
        sqlSession.close();
    }

    /**
     * 新增
     */

   /* @Test
    public  void testSave(){

        Student student = new Student();
        student.setStuName("jack");
        student.setAge(34);
        int res = studentMapper.save(student);

        logger.debug("受影响的行数:{}",res);


        int id = student.getId();
        logger.debug("新增加的id为:{}",id);
        sqlSession.commit();
    }*/

  /*  @Test
    public void findAll(){
        List<Student> studentList = studentMapper.findAll();
        for(Student student:studentList){

            logger.debug("所有学生:{}",student);
        }
    }
*/
    /*//进行分页
    @Test
    public void testFindpage(){
        List<Student> studentList = studentMapper.findPage(0,3);
        for(Student student:studentList){

            logger.debug("所有学生:{}",student);
        }
    }*/


    //利用map集合进行多个参数的传参
 /*   @Test
    public void testFindPageByMap(){
        Map<String ,Integer> maps = new HashMap<>();
        maps.put("start",0);
        maps.put("pageSize",3);

        List<Student> studentList = studentMapper.findPageByMap(maps);
        for(Student student:studentList){

            logger.debug("所有学生:{}",student);
        }


    }
*/


   /* @Test
    public void findById(){
        Student student = studentMapper.findById(8);
        logger.debug("id为8的学生信息为:{}",student);
    }

    @Test
    public void delById(){

        studentMapper.delById(5);
        sqlSession.commit();

    }*/
/*

    @Test
    public void testUpdate(){
        Student student = studentMapper.findById(8);
        student.setAge(100);

        studentMapper.update(student);
        sqlSession.commit();
    }
*/


/*一对多，多对一，多对多关系===========================================================================*/


    /*一对一进行查询*/
    @Test
    public void testFindSchoolById(){
        Student student = studentMapper.findSchoolById(11);
        logger.debug("student:{}",student);

    }

}
