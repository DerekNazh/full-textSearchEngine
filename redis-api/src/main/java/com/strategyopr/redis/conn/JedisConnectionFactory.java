package com.strategyopr.redis.conn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static JedisPool jedisPool;
    static{
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxTotal(8);
        conf.setMaxIdle(8);
        conf.setMinIdle(0);
        conf.setMaxWaitMillis(200);
        jedisPool = new JedisPool(conf,"localhost",6379);
    }
        public static Jedis getJedis(){
        return jedisPool.getResource();
        }
}
