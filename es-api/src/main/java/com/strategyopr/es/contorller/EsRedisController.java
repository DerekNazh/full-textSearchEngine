package com.strategyopr.es.contorller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.strategyopr.redis.conn.JedisConnectionFactory;



import redis.clients.jedis.Jedis;

/**
 * 把查询得到的文章ID集合发送给Redis缓存
 */
public class EsRedisController {
     public void idsToRedis(String keyword,int[] ids) throws JsonProcessingException {
          Jedis conn = JedisConnectionFactory.getJedis();
          conn.auth("user", "pass");
          conn.select(0);

          ObjectMapper oM = new ObjectMapper();
          String idsJsonStr = oM.writeValueAsString(ids);
          conn.set(keyword,idsJsonStr);
     }

}
