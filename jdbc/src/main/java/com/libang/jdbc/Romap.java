package com.libang.jdbc;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author libang
 * @date 2018/7/16 20:47
 */
public interface Romap<T> {

    T mapRow(ResultSet res) throws SQLException;
}
