package com.libang;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author libang
 * @date 2018/7/19 11:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations = "classpath:spring.xml")*/
@ContextConfiguration(classes = Application.class)
public class BaseTestCase {
}
