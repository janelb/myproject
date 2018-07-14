import com.libang.proxy.Dell;
import com.libang.proxy.MyInvocationHandler;
import com.libang.proxy.Sale;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author libang
 * @date 2018/7/14 19:33
 */
public class MyInvocationHandlerTestCase {

    @Test
    public void testMyInvocationHandler(){
        Dell dell = new Dell();
        //创建MyInvocationHandler 目标对象
        MyInvocationHandler invocationHandler = new MyInvocationHandler(dell);

        //动态产生代理对象
        Sale sale = (Sale) Proxy.newProxyInstance(Dell.class.getClassLoader(),Dell.class.getInterfaces(),invocationHandler);

        sale.salePc();
    }


}
