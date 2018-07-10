package wx;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.sound.midi.Soundbank;

public class JedisTest {

    @Test
    public void Test1(){
        //这里的ip必须和配置时一致
        //1.获得连接对象
        Jedis jedis=new Jedis("127.0.0.1",6379);
        //2.获得数据
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    public void Test2(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("key1","aaa");
        System.out.println(jedis.get("key1"));
        jedis.close();
    }

    @Test
    public void test3(){
        //创建池子的配置对象
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(30);//最大闲置个数
        poolConfig.setMinIdle(10);//最小闲置个数
        poolConfig.setMaxTotal(50);//最大连接数

        //创建一个redis的连接池
        JedisPool pool=new JedisPool(poolConfig,"127.0.0.1",6379);

        //从池子中获取redis的连接资源
        Jedis jedis=pool.getResource();

        //操作数据库
        jedis.set("xxx","yyyy");
        System.out.println(jedis.get("xxx"));

        //关闭资源
        jedis.close();
        pool.close();
    }
}
