import com.libang.proxy.Dell;
import com.libang.proxy.ProxyInt;
import org.junit.Test;

/**
 * @author libang
 * @date 2018/7/14 19:21
 */
public class ProxyTestCase {


    @Test
    public void testProxy(){
       /* ProxyInt proxyInt = new ProxyInt();
        proxyInt.salePc();*/
       Dell dell = new Dell();
       ProxyInt proxyInt = new ProxyInt(dell);
       proxyInt.salePc();

    }


}
