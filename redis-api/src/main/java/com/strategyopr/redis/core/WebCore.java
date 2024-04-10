package com.strategyopr.redis.core;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strategyopr.redis.conn.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
public class WebCore {
    // 1.缓存用户关键词经过倒排索引产生的文章ID计划
    public  int[] getIDsByK(String key) throws JsonProcessingException {
        Jedis jedis = JedisConnectionFactory.getJedis();
        String idsJson = jedis.get(key);
        ObjectMapper oM = new ObjectMapper();
        return oM.readValue(idsJson, int[].class);
    }
}
