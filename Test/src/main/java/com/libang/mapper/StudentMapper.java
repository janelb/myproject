package com.libang.mapper;

import com.libang.entity.Student;
import com.libang.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/11 11:08
 */
public interface StudentMapper {


            List<Student> findAll();
            Student findById(Integer id);
            int addStudent(Student student);
            List<Student> findByKeys(@Param("stuName") String stuName);

                /**
                 * 使用student对象
                 * @param student
                 */
                void update(Student student);

            /**
             * 多个参数查询使用map
             * @param maps
             * @return
             */
            List<Student> findByPage(Map<String , Object> maps );

            /**
             *多个参数查询使用@Param注释
             * @param stuName
             * @param schoolId
             * @return
             */
            List<Student> findByParam(@Param("stuName") String stuName , @Param("schoolId") Integer schoolId);

            /**
             * 进行分页查询
             * @param start
             * @param pageSize
             * @return
             */
            List<Student> findPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

            /**
             * 查询指定的多个数据
             * @param idList
             * @return
             */
            List<Student> findByIds(@Param("idList") List<Integer> idList);


            /**
             * 一对一或多对一查询
             * @param id
             * @return
             */
            Student findByStudentId(Integer id);

            /**
             * 一对多查询
             * @param id
             */
            Student findTypeByStudentId(Integer id);

            /**
             * 进行批量插入
             * @param typeList
             */
            void addBatch(@Param("typeList") List<Type> typeList);


}
