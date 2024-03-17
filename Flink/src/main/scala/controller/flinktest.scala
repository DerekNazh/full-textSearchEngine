package controller

import commons.EsDMLUtil
import conn.{EsConnector, FlinkConnector}
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.client.Requests
import util.PropertiesLoader

import java.util

object flinktest {
  def main(args: Array[String]): Unit = {

          MovieAnalysis.run()
  }


}
