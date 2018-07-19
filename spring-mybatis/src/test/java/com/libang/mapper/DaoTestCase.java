package com.libang.mapper;


import com.libang.BaseTestCase;
import com.libang.entity.Book2;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author libang
 * @date 2018/7/19 11:09
 */
public class DaoTestCase extends BaseTestCase {

    @Autowired
    private Book2Mapper book2Mapper;

    @Test
    public void testFindById(){
        Book2 book2 = book2Mapper.selectByPrimaryKey(6);
        System.out.println(book2);


    }

    @Test
    public void testSave(){
        Book2 book2 = new Book2();
        book2.setAuthor("唐");
        book2.setName("xixixxi");
        book2.setTotal(200);
        book2.setNownum(200);
        book2.setIsbn("23433");
        book2Mapper.insert(book2);
        int id = book2.getId();
        System.out.println(id);

    }



}
