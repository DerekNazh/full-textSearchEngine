package com.strategyopr.redis.conn;

import redis.clients.jedis.Jedis;

public class RedisConnector {
    public static void main(String[] args) {


    }
    public static Jedis getConnection(){
        Jedis cli = new Jedis("localhost", 6379);
        return cli;
    }
    public  static boolean close(Jedis conn){
        if (conn != null) {
            conn.close();
        }
        return true;
    }
}
