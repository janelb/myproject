package com.libang.test;

import com.libang.entity.Student;

import com.libang.util.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author libang
 * @date 2018/7/9 20:58
 */
public class StudentTestCase {

    private  SqlSession sqlSession;



    @Before
    public void before(){
        sqlSession = MybatisUtils.getSqlSession();

    }

    @After
    public  void after(){
        sqlSession.close();

    }


    Logger logger = LoggerFactory.getLogger(StudentTestCase.class);

    /**
     * 新增数据
     *
     * @throws IOException
     */


    /*@Test
    public void testSave() throws IOException {

       *//* Reader reader = Resources.getResourceAsReader("mybatis.xml");
        System.out.println("你好");

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


       SqlSession sqlSession = sqlSessionFactory.openSession(true);*//*

       *//* SqlSession sqlSession = MybatisUtils.getSqlSession();
*//*
        Student student = new Student();
        student.setStuName("tom");
        student.setAge(23);

        int res = sqlSession.insert("com.libang.mapper.StudentMapper.save", student);

        logger.debug("受影响的行数:{}",res);

        sqlSession.commit();
        Assert.assertEquals(1, res);
       *//* sqlSession.close();*//*
    }*/

    /**
     * 查询所有
     */
   /* @Test*/
    /*public void testFindAll() {
        *//*SqlSession sqlSession = MybatisUtils.getSqlSession();*//*
        List<Student> studentList = sqlSession.selectList("com.libang.mapper.StudentMapper.findAll");
        for (Student student : studentList) {
            System.out.println(student);
        }
       *//* sqlSession.close();*//*

    }*/

    /**
     * 根据Id进行查询
     */
    /*@Test
    public void testFindOne() {
        *//*SqlSession sqlSession = MybatisUtils.getSqlSession();*//*
        Student student = sqlSession.selectOne("com.libang.StudentMapper.findByid", 2);
        System.out.println(student);
       *//* sqlSession.close();*//*
    }*/

    /**
     * 根据id进行删除
     */
  /*  @Test
    public void testDelId() {
      *//*  SqlSession sqlSession = MybatisUtils.getSqlSession(true);*//*
        Student student = sqlSession.selectOne("com.libang.StudentMapper.findById",2);
        sqlSession.delete("com.libang.StudentMapper.delById", 2);
        sqlSession.commit();
        *//*sqlSession.close();*//*
    }
*/
    /**
     * 根据id进行删除
     */
   /* @Test
    public void testUpdate() {
        *//*SqlSession sqlSession = MybatisUtils.getSqlSession(true);*//*
        Student student = sqlSession.selectOne("com.libang.StudentMapper.findById", 2);

        student.setStuName("jack");
        student.setAge(34);
        sqlSession.update("com.libang.StudentMapper.update", 2);
        *//*
        自动提交事务
        * *//*
        sqlSession.commit();
        *//*sqlSession.close();*//*

    }
*/







}
