import com.alibaba.fastjson.JSON;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.*;

/**
 * @author libang
 * @date 2018/8/21 12:20
 */
public class RedisTest {


    /*使用集群方式进行取值*/
    @Test
    public void clusterConn() throws IOException {
        //获取来接池
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(2);

        Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
        hostAndPorts.add(new HostAndPort("192.168.1.166", 6000));
        hostAndPorts.add(new HostAndPort("192.168.1.166", 6001));
        hostAndPorts.add(new HostAndPort("192.168.1.166", 6002));
        hostAndPorts.add(new HostAndPort("192.168.1.166", 6003));
        hostAndPorts.add(new HostAndPort("192.168.1.166", 6004));
        hostAndPorts.add(new HostAndPort("192.168.1.166", 6005));

        JedisCluster jedisCluster = new JedisCluster(hostAndPorts, config);
        String name = jedisCluster.get("name");
        System.out.println(name);
        jedisCluster.close();

    }


    @Test
    public void setUser2() {
        //获取来接池
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(2);
        JedisPool jedisPool = new JedisPool("192.168.1.166", 6379);

        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "lisi");
        jedis.close();

    }


    /**
     * 使用对象封装json
     */
    @Test
    public void setUser() {
        Jedis jedis = new Jedis("192.168.1.166", 6379);
        User user = new User();
        user.setId(12);
        user.setUserName("aaaa");
        user.setAddress("南京");
        //封装json
        String json = JSON.toJSONString(user);
        jedis.set("user:100", json);
        jedis.close();

    }

    /**
     * 获取json解析json
     */
    @Test
    public void getUser() {
        Jedis jedis = new Jedis("192.168.1.166", 6379);
        //获取json
        String json = jedis.get("user:100");
        //解析json
        User user = JSON.parseObject(json, User.class);
        System.out.println(user);
        jedis.close();

    }


    @Test
    public void testConn() {
        //获取来接
        Jedis jedis = new Jedis("192.168.1.166", 6379);

        //进行设置数据
     /*  jedis.set("name","tom");
        jedis.set("age","23");*/

        //进行取值
        String name = jedis.get("name");
        String age = jedis.get("age");
        System.out.println(name);
        System.out.println(age);

        //释放资源
        jedis.close();
    }

    @Test
    public void setHash() {
        Jedis jedis = new Jedis("192.168.1.166", 6379);
        jedis.hset("user:1001", "name", "tom");

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "23");
        params.put("age", "33");
        params.put("address", "北京");

        jedis.hmset("user:1001", params);

        jedis.close();
    }


    @Test
    public void getHash() {
        Jedis jedis = new Jedis("192.168.1.166", 6379);
        //获取hash数组
        Object[] array = jedis.hkeys("user:1001").toArray();
        //新建数组
        String[] strarray = new String[array.length];
        //进行迭代hash数组
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            //将数据转化为toString
            String str = obj.toString();
            //赋值给String数组的第i位置
            strarray[i] = str;
        }
        List<String> values = jedis.hmget("user:1001", strarray);

        for (String value : values) {
            System.out.println(value);
        }
        jedis.close();
    }


}
