import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author libang
 * @date 2018/8/21 19:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-redis.xml")
public class SpringDataRedisTest {


    private RedisTemplate<String ,User> redisTemplate;

    //默认的是Object设置序列化规则
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        //this.redisTemplate.setValueSerializer(new StringRedisSerializer());
        //使用jackSon使值进行序列化
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    //操作对象 使用集群也是一样
    @Test
    public void setUser(){
        User user = new User();
        user.setId(23);
        user.setUserName("jack");
        user.setAddress("北京");
        //使用jackson使值自动封装为json字符串
        redisTemplate.opsForValue().set("user:1002",user);
    }

    @Test
    public void getUser(){
        //读取时直接转化为json字符串
        User user = redisTemplate.opsForValue().get("user:1002");
        System.out.println(user);

    }



 /*   @Test
    public void setRedisTemplate() {

        redisTemplate.opsForValue().set("name:1001", "tom");
    }

    @Test
    public void getRdisTemplate(){
        String value =  redisTemplate.opsForValue().get("name:1001");
        System.out.println(value);
    }*/
}
