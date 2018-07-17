package com.libang.jdbc;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author libang
 * @date 2018/7/16 20:47
 */
public class BeanRomap<T> implements Romap<T> {

    private Class<T> calzz;
    public BeanRomap(Class<T> clazz){
        this.calzz = clazz;
    }
    @Override
    public T mapRow(ResultSet res) throws SQLException {
            Class clazz = T.c





        return null;
    }
}
