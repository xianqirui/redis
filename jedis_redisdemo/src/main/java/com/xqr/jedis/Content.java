package com.xqr.jedis;

import redis.clients.jedis.Jedis;

public class Content {
    public void content(){
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
    }
}
