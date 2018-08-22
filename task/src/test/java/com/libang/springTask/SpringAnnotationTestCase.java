package com.libang.springTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/16 15:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task-annotation.xml")
public class SpringAnnotationTestCase {

    @Test
    public  void taskAnnotationJob() throws IOException {

        System.out.println("spring start.....");
        System.in.read();



    }


}
