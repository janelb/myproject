package com.libang.mapper;

import com.libang.entity.Student;
import com.libang.entity.Type;
import org.apache.ibatis.annotations.Param;

import javax.swing.plaf.synth.SynthUI;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/10 10:53
 */
public interface StudentMapper {
    /**
     *新增学生
     * @param
     * @return
     */
  /*  int save(Student student);
    List<Student> findAll();
    Student findById(Integer id);
    void delById(Integer id);
    void update(Student student);

    List<Student> findPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    List<Student> findPageByMap(Map<String ,Integer> map);*/


/*==============================================*/
        Student findSchoolById(Integer id);
        Student findTypeById(Integer id);
        int addBatch( @Param("typeList") List<Type> typeList);



}
