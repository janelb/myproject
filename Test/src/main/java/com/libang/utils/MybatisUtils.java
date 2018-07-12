package com.libang.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.Reader;

/**
 * 使用单例模式创建sqlSessionFactory对象
 * @author libang
 * @date 2018/7/11 11:06
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        Reader reader=null;
        try {
             reader  = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory(){

        return sqlSessionFactory;

    }

    public static SqlSession getSqlSession(boolean autoCommit){
            return getSqlSessionFactory().openSession(autoCommit);

    }
    public  static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();

    }




}
