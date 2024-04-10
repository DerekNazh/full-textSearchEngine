package com.strategyopr.dirver;


import com.strategyopr.dataimport.core.DoubanDemo;
import com.strategyopr.dirver.inf.AbstractDriver;
import com.strategyopr.es.core.EsDemo;
import com.strategyopr.flink.scala.controller.MovieAnalysis;
import com.strategyopr.redis.core.WebCore;

import java.io.IOException;

public class SearchEngineDriver extends AbstractDriver {
    public static  void write(){
    //写流程

    // 1.kafka拉取数据
        DoubanDemo.work();
    //2.flink拉取计算，
    // 并把建立索引的字段放入Es，把记录的全量数据放入Hbase
         MovieAnalysis.run();

    }

    /**
     * 当用户查询数据时，集群通过最佳的方式返回对应的搜索内容，以json字符串的形式返回
     *
     * @param UserKw
     * @return
     */
    public static String UserSearch(String UserKw) {


        return  "";
    }
}
