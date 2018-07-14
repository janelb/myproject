import com.libang.service.BaseService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @author libang
 * @date 2018/7/14 12:44
 */
public class BaseTestCase {


    @Test
    public void testBase(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseService baseService = (BaseService) context.getBean("baseService");
            System.out.println(baseService.getId());
            System.out.println(baseService.getName());
            System.out.println(baseService.getScore());
         /*   System.out.println(baseService.getNumSets());
            System.out.println(baseService.getStringList());*/

         /*List集合*/
        List<String> stringList = baseService.getStringList();
        for(String name : stringList){
            System.out.println("list集合"+name);
        }
        /*Set集合*/
        Set<Integer> sets = baseService.getNumSets();
        for (Integer num : sets){
            System.out.println(num);
        }
        /*Map集合*/
        Map<String ,String> maps = baseService.getMaps();
        for(Map.Entry<String,String> entry : maps.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        /*Properties类似map集合*/
        Properties props  = baseService.getProperties();

        /*获取所有key值*/
        Enumeration<Object> keys = props.keys();
        /*使用迭代器*/
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value  = props.get(key);

            System.out.println(key);
            System.out.println(value);

        }

    }







}
