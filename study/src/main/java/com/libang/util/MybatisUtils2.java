package com.libang.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.Reader;

/**
 * @author libang
 * @date 2018/7/18 13:43
 */
public class MybatisUtils2 {
        private static SqlSessionFactory sqlSessionFactory;
    static {
        Reader reader=null;
        try {

             reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**/
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

    }

        //进行封装，根据sqlSessionFactory对象创建sqlSession对象
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    /**
     * @param autoCommit
     * @return
     */
    public static SqlSession getSqlSession(boolean autoCommit){
        return getSqlSessionFactory().openSession(autoCommit);

    }

    /**
     * @return
     */
    public static SqlSession getSqlSession(){

        return getSqlSessionFactory().openSession();
    }
}
