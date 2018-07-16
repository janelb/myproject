package aop;

import com.libang.proxy.Sale;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author libang
 * @date 2018/7/16 12:40
 */
public class AopTestCase {


    @Test
    public void testAop(){
            //进行配置文件加载
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop.xml");
        // 此时获得的lenovo是增强过后的动态代理类对象，因此不能强转为Lenovo,否则会抛出java.lang.ClassCastException:
        Sale sale = (Sale)context.getBean("dell");

        sale.salePc();
        int price = sale.salePrice();



    }
}
