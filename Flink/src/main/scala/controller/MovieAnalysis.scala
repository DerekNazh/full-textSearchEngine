package controller

import Bean.BookBean
import commons.{EsDMLUtil, FlinkHbaseSink}
import conn.{EsConnector, FlinkConnector}
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.client.{Requests, RestHighLevelClient}

import java.util
import _root_.util.PropertiesLoader

object MovieAnalysis {

  def run(): Unit = {
    /** 数据格式
     * ID,Movie_Name_EN,Movie_Name_CN,Crawl_Date,Number,Username,Date,Star,Comment,Like
     * 0,Avengers Age of Ultron,复仇者联盟2,2017-01-22,1,然潘,2015-05-13,3, 连奥创都知道整容要去韩国。,2404
     */
    //连接对象
    val conn = FlinkConnector.getConnection

    //读取文件
    val ds = conn.readTextFile("tiny_Moviesdata.csv")

    //分割过滤封装
    val mapds = ds.map(_.split(",")).filter(_.size == 10)

      //Sink部分
    /**
     * 1. hbase            存储除Crawl_Date数据
     * 2.elasticsearch     需要的字段 ID,Movie_Name_EN,MovieName_CN,Username,Comment
     *                     对应的集合位置0,1,2,5,8
     * 3.hive              存储全部数据，用来离线分析
     */
    val beands = mapds.map(element => {
      val bean = new BookBean(
        element(0),
        element(1),
        element(2),
        element(3),
        element(4),
        element(5),
        element(6),
        element(7),
        element(8),
        element(9),
      )
      bean
    })
    val esdata = mapds.map(
      element => {
        (element(0), element(1),element(2), element(5), element(8))
      })


    //1.hbase部分
//    beands.addSink(new FlinkHbaseSink())

    //2.elasticsearch
    val hostList = new util.ArrayList[HttpHost]()
    //去工作目录下获取es.properties目录下的hostname和port
    hostList.add(new HttpHost(PropertiesLoader.load("es.properties").getProperty("hostname"),
      PropertiesLoader.load("es.properties").getProperty("port").toInt))


    val esfun = new ElasticsearchSinkFunction[(String,String, String, String, String)] {
      private val conn: RestHighLevelClient = EsConnector.getConnection

      override def open(): Unit = {
        //判断Es中book索引是否存在，存在不用动，不存在需创建
            if(!EsDMLUtil.existsIndex(conn,"book1")){
              EsDMLUtil.createIndex(conn,"book1")
            }
      }
      override def process(t: (String, String, String, String,String),
                           runtimeContext: RuntimeContext,
                           requestIndexer: RequestIndexer): Unit = {
        val fields= ("id","movie_Name_en","movie_Name_cn","username","comment")

        val indexreq = Requests.indexRequest("book")

        indexreq.id(t._1)
        val map = new util.HashMap[String,String]()
            map.put(fields._1,t._1)
            map.put(fields._2,t._2)
           map.put(fields._3,t._3)
           map.put(fields._4,t._4)
            map.put(fields._5,t._5)
        indexreq.source(map)
        requestIndexer.add(indexreq)
    //    ID,Movie_Name_EN,Movie_Name_CN,Username,Comment
      }
    }
    esdata.addSink(new ElasticsearchSink.Builder(hostList,esfun).build())
    conn.execute()
  }
}
