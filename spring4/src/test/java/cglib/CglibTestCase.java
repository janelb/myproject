package cglib;

import com.libang.cglib.Sale;
import com.libang.cglib.SubjectMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

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
        //设置代理对象的代理行为
        enhancer.setCallback( new SubjectMethodInterceptor());

        //动态代理产生对象
        Sale sale = (Sale)enhancer.create();
        //输入被代理对象的类和名
        System.out.println(sale.getClass().getName());
        sale.salePc();
    }


    //方法二：使用内部类的形式实现动态代理
    @Test
    public void testCglib2(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sale.class);
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * @param target 目标对象
             * @param method
             * @param args 参数列表
             * @param methodProxy 产生父类方法的代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                //执行父类（目标对象） 的方法
                Object result = methodProxy.invokeSuper(target,args);
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

                return result;
            }
        });
        //动态产生动态代理对象
        Sale sale = (Sale) enhancer.create();
        sale.salePc();



    }
}



