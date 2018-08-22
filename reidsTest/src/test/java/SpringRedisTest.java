import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @author libang
 * @date 2018/8/21 18:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringRedisTest {

/*    @Autowired
    private JedisPool jedisPool;*/

    /*集群*/
    @Autowired
    private JedisCluster jedisCluster;

    /*集群*/
    @Test
    public void getClusetr(){

        System.out.println(jedisCluster.get("name"));

    }




/*
    @Test
    public void setValue() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "tom");
        jedis.close();
    }

    @Test
    public void getValu() {
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }*/
}
