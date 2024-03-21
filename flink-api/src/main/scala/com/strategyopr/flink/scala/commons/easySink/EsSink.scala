package com.strategyopr.flink.scala.commons.easySink

import com.strategyopr.es.commons.EsDDLUtil
import com.strategyopr.flink.bean.{BookBean, EsBookBean}
import com.strategyopr.es.conn.EsConnector
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.{RequestOptions, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentType



class EsSink extends RichSinkFunction[EsBookBean]{
  var cli:RestHighLevelClient = null
  override def open(parameters: Configuration): Unit = {
    cli = EsConnector.getConnection("localhost",9200)
  }


  override def invoke(value: EsBookBean, context: SinkFunction.Context): Unit = {
        //获取请求
        val response = EsDDLUtil.insetRow(cli, "book", value.getID, value)
       println(response)
  }
  override def close(): Unit =  EsConnector.close()

}
