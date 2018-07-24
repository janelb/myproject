import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libang.entity.Student;
import com.libang.entity.StudentExample;
import com.libang.mapper.StudentMapper;
import com.libang.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/12 17:58
 */
public class StudentMapperTestCase {
    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    Logger logger  = LoggerFactory.getLogger(StudentMapperTestCase.class);

    @Before
    public void before(){
        sqlSession = MybatisUtils.getSqlSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }


    /*根据id进行查询*/
    @Test
    public void testFindById(){
        Student student = studentMapper.selectByPrimaryKey(11);
        System.out.println(student);
    }

    /*模糊查询*/
    @Test
    public void testFindByKeys(){
        StudentExample studentExample = new StudentExample();
                studentExample.createCriteria().andStuNameLike("%张%");
        List<Student> studentList =studentMapper.selectByExample(studentExample);
        for(Student student : studentList){
            System.out.println(student);
        }
    }

    /*新增*/
    @Test
    public void  testAddStudent(){
        Student student = new Student();
        student.setStuName("赵大山");
        student.setSchoolId(2);
        int res = studentMapper.insert(student);
        sqlSession.commit();
        System.out.println("影响行数:" + res);

        int code = student.getId();
        System.out.println("id为：" + code);
    }

    /*根据id进行删除*/
    @Test
    public void testDeleteByid(){

        studentMapper.deleteByPrimaryKey(23);
        sqlSession.commit();
    }

    /*根据模糊字段进行删除*/
    @Test
    public void testDelByExmple(){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuNameLike("%山%");
        studentMapper.deleteByExample(studentExample);
        sqlSession.commit();
    }

    /*修改*/
    @Test
    public  void  testUpdate(){
        Student student = studentMapper.selectByPrimaryKey(21);

        student.setStuName("张老二");
        studentMapper.updateByPrimaryKey(student);
        sqlSession.commit();
    }

    /*新创建对象根据id进行修改*/
    @Test
    public void testUpdateSelectiveByid(){
        Student student = new Student();
        student.setId(21);
        student.setStuName("王大狗");
        studentMapper.updateByPrimaryKeySelective(student);
        sqlSession.commit();
    }

    /*分页*/
    @Test
    public void testLimit(){

       /* //从第二条开始取4条
        PageHelper.offsetPage(2,4);*/

        //表示从第一页开始取3条
        PageHelper.startPage(1,3);
        StudentExample studentExample = new StudentExample();
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for(Student student : studentList){
            System.out.println(student);
        }
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getPageSize());
    }

      /* 有选择的条件的查询*/
    @Test
      public void testDesc(){

          StudentExample studentExample = new StudentExample();
          //有选择的条件的查询，查询id是12 或 id是14的学生
          studentExample.or().andIdEqualTo(12);
          studentExample.or().andIdEqualTo(14);
          List<Student> studentList = studentMapper.selectByExample(studentExample);
          for(Student student : studentList){
              System.out.println(student);
          }
    }

    /*排序*/
    @Test
    public void testOrderBy(){
        StudentExample studentExample =new StudentExample();
        //根据id进行排序
        studentExample.setOrderByClause("id desc");
        //去除重复
        studentExample.setDistinct(true);
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for(Student student : studentList){
            System.out.println(student);
        }

    }



    /*插入*/
    @Test
    public void testInsert(){
        Student student = new Student();
        student.setStuName("aaa");
        studentMapper.insertSelective(student);
        sqlSession.commit();

    }




}
