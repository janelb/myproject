package com.libang.service;

import com.libang.dao.StudentDao;
import com.libang.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/17 19:48
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    //对方法进行事务，所有异常都进行回滚，默认的是运行时异常进行回滚
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<Student> studentList)throws Exception{

        for(Student student : studentList){
            studentDao.save(student);

        }


    }
}
