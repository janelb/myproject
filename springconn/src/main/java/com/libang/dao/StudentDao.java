package com.libang.dao;

import com.libang.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/17 11:28
 */
@Repository
public class StudentDao {

        @Autowired
       private JdbcTemplate jdbcTemplate;

        public void save(Student student){
            String sql ="insert into student(stu_name,school_id)values(?,?)";
             jdbcTemplate.update(sql,student.getStuName(),student.getSchoolId());
    }

    public Student findById(Integer id){
            String sql = "select*from student where id =?";
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class),id);

    }

    public List<Student> findAll(){
            String sql = "select*from student";
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Student.class));
    }

    public int count(){
            String sql = "select count(*) from student";
            return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Long>()).intValue();

    }

    public List<Map<String ,Object>> findMapList(){
            String sql = "select*from student";
            return jdbcTemplate.query(sql,new ColumnMapRowMapper());


    }




}
