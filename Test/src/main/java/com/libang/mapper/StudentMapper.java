package com.libang.mapper;

import com.libang.entity.Student;
import com.libang.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/11 11:08
 */
public interface StudentMapper {


            List<Student> findAll();

            @Select("SELECT id, stu_name ,school_id from student  where id = #{id}")
            Student findById(Integer id);


            @Insert("INSERT into student (stu_name,school_id)values(#{stuName},#{schoolId})")
            @Options(useGeneratedKeys = true ,keyProperty = "id")
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
             * 根据学生的i查询对应的学校
             * @param id
             * @return
             */

            @Select("SELECT id, stu_name ,school_id from student  where .student.id = #{id}")
            @Results(value={
                    @Result(column = "id",property = "id"),
                    @Result(column = "stu_name", property = "stuName"),
                    @Result(column = "school_id",property = "schoolId"),
                    @Result(column = "school_id" ,property = "school" ,one = @One(
                        select = "com.libang.mapper.SchoolMapper.findById"

                    ))
            })
            Student findByStudentId(Integer id);

            /**
             * 一对多查询
             * 根据学生id查询对应类型
             * @param id
             */


            @Select("SELECT id, stu_name ,school_id from student  where .student.id = #{id}")
            @Results(value = {
                    @Result(column = "id",property = "id"),
                    @Result(column = "stu_name", property = "stuName"),
                    @Result(column = "school_id",property = "schoolId"),
                    @Result(column = "id",property = "typeList", many = @Many(
                            select = "com.libang.mapper.TypeMapper.findByStudentId"

                    ))

            })
            Student findTypeByStudentId(Integer id);





            /**
             * 进行批量插入
             * @param typeList
             */
            void addBatch(@Param("typeList") List<Type> typeList);


}
