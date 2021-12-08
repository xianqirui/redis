package com.xqr.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class jedisDemo01 {
    public static void main(String[] args) {
        //创建对象
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        //测试
        String value=jedis.ping();
        System.out.println(value);
        jedis.close();
    }
    //操作key
    @Test
    public void demo01(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        //添加
        jedis.set("name", "lucy");
        //获取
        String name = jedis.get("name");
        System.out.println(name);

        //设置多个key
        jedis.mset("k1","v1","k2","v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

        Set<String> keys = jedis.keys("*");
        for(String key:keys){
            System.out.println(key);
        }
    }

    //list操作
    @Test
    public void demo02(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");

        jedis.del("names");
        jedis.lpush("names","lucy","mary","tom");
        List<String> names = jedis.lrange("names", 0, -1);
        System.out.println(names);
    }

    //set操作
    @Test
    public void demo03(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        jedis.sadd("names1","lucy","mary");
        Set<String> names = jedis.smembers("names1");
        System.out.println(names);
    }

    //hash操作
    @Test
    public void demo04(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        jedis.hset("users","age","20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
    }
    @Test
    public void close(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        jedis.close();
    }

    //zset操作
    @Test
    public void demo05(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        jedis.zadd("china",100,"shanghai");
        Set<String> china = jedis.zrange("china", 0, -1);
        System.out.println(china);

    }
}
