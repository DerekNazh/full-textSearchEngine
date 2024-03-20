package com.strategyopr.flink.scala.commons.easySink

import com.strategyopr.flink.bean.BookBean
import com.strategyopr.hbase.commons.HbaseDDLUtil
import com.strategyopr.hbase.conn.HbaseConnector
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.hadoop.hbase.client.Connection
import com.strategyopr.util.StringTuple2

import java.util
class HbaseSink extends RichSinkFunction[BookBean]{
  var  conn:Connection =_
  override def open(parameters: Configuration): Unit = {

    //加载 属性
    var tuples = new util.ArrayList[StringTuple2]()
    val hostnamePro = new StringTuple2("hbase.zookeeper.quorum", "node1,node2,node3")
      tuples.add(hostnamePro)


    //判断表是否存在     1不存在就创建 2.存在就使用此表
      //封装再写
  }
  override def close(){
    HbaseConnector.close()
  }

  override def invoke(value: BookBean, context: SinkFunction.Context): Unit = {

         HbaseDDLUtil.insertObj(conn,
                "myNamespace",
                "book",
                          value.getID,
           "info1",value)
  }
}
