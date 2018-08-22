import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author libang
 * @date 2018/8/21 20:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redisson.xml")
public class SpringRedisson {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void setValue(){
        RBucket<Object> MyName =  redissonClient.getBucket("MyName");
        MyName.set("tom");
    }
    @Test
    public void getValue(){
        RBucket<Object> key = redissonClient.getBucket("MyName");
        System.out.println(key.get());
    }

}
