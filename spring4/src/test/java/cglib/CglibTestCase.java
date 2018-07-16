package cglib;

import com.libang.cglib.Sale;
import com.libang.cglib.SubjectMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author libang
 * @date 2018/7/16 11:03
 */
public class CglibTestCase {

    @Test
    public void testCglib(){
        //创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //设置目标对象
        enhancer.setSuperclass(Sale.class);
        enhancer.setCallback( new SubjectMethodInterceptor());

        //动态代理产生对象
        Sale sale = (Sale)enhancer.create();
        //输入被代理对象的类和名
        System.out.println(sale.getClass().getName());
        sale.salePc();
    }
}
