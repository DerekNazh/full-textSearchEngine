package com.strategyopr.flink.scala.commons.easySink

import com.strategyopr.flink.bean.BookBean
import com.strategyopr.es.conn.EsConnector
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.{RequestOptions, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentType



class EsSink extends RichSinkFunction[BookBean]{
  var cli:RestHighLevelClient = null
  override def open(parameters: Configuration): Unit = {
    cli = EsConnector.getConnection("localhost",9200)
  }


  override def invoke(value: BookBean, context: SinkFunction.Context): Unit = {
        //获取请求
       val indexReq = new IndexRequest("book")
       indexReq.id(value.getID)
       val mapper = new ObjectMapper()
       val bookBean = mapper.writeValueAsString(value)
       indexReq.source(bookBean,XContentType.JSON)
    val res = cli.index(indexReq, RequestOptions.DEFAULT)
    print(res)
  }
  override def close(): Unit =  EsConnector.close()

}
