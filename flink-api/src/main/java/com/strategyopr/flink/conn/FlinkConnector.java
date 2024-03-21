package com.strategyopr.flink.conn;

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;

public class FlinkConnector {
    //本地连接
private static StreamExecutionEnvironment env = null;
   public static StreamExecutionEnvironment getConnection(){
        if(env == null) {
            env = StreamExecutionEnvironment.getExecutionEnvironment();
        }
        return env;
    }
}
