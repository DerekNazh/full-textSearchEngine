package com.strategyopr.flink.scala.controller



import com.strategyopr.flink.bean.{BookBean, EsBookBean}
import com.strategyopr.flink.conn.FlinkConnector
import com.strategyopr.flink.scala.commons.easySink.{EsSink, HbaseSink}
import com.strategyopr.util.PropertiesLoader
import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.common.time.Time
import org.apache.flink.streaming.api.scala.createTypeInformation

import java.util.concurrent.TimeUnit



object MovieAnalysis {

  def run(): Unit = {
    /** 数据格式
     * ID,Movie_Name_EN,Movie_Name_CN,Crawl_Date,Number,Username,Date,Star,Comment,Like
     * 0,Avengers Age of Ultron,复仇者联盟2,2017-01-22,1,然潘,2015-05-13,3, 连奥创都知道整容要去韩国。,2404
     */
    //连接对象
    val conn = FlinkConnector.getConnection

    conn.setRestartStrategy(RestartStrategies.noRestart())


    //读取文件
    val ds = conn.readTextFile("C:\\Users\\86130\\Desktop\\tmp\\毕业设计数据样例\\项目数据\\book\\DMSC\\DMSC _tail.csv")

    //分割过滤封装  判断是否是正常数据
    val mapds = ds.map(_.split(",")).filter(_.size == 10)

      //Sink部分
    /**
     * 1. hbase            存储除Crawl_Date数据
     * 2.elasticsearch     需要的字段 ID,Movie_Name_EN,MovieName_CN,Username,Comment
     *                     对应的集合位置0,1,2,5,8
     * 3.hive              存储全部数据，用来离线分析
     */
    val beands = mapds.map(element => {
    val bean =  new EsBookBean(
        element(0),
        element(1),
        element(2),
        element(5),
        element(8)
      )
      bean
    })

    //1.hbase部分
//    beands.addSink(new FlinkHbaseSink())

    //2.elasticsearch
    //去工作目录下获取es.properties目录下的hostname和port

    beands.addSink(new EsSink)
    conn.execute()
  }
}
