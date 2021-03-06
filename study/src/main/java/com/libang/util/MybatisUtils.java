package com.libang.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author libang
 * @date 2018/7/9 20:37
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static{
        Reader reader = null;

        try {
            //从classPath中读取配置文件
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据SqlSessionFactoryBuilder对象创建sqlSessionFactory对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

    }


    public static SqlSessionFactory getSqlSessionFactory(){

        return sqlSessionFactory;
    }
    public static SqlSession getSqlSession(boolean autoCommit){

        return getSqlSessionFactory().openSession(autoCommit);

    }
    public static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();

    }

}
