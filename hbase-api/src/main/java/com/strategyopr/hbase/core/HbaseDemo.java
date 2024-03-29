package com.strategyopr.hbase.core;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strategyopr.es.conn.EsConnector;
import com.strategyopr.es.commons.EsDMLUtil;
import com.strategyopr.es.core.EsDemo;
import com.strategyopr.hbase.commons.HbaseDDLUtil;
import com.strategyopr.hbase.commons.HbaseDMLUtil;
import com.strategyopr.hbase.conn.HbaseConnector;
import org.apache.hadoop.hbase.client.Connection;
import org.elasticsearch.client.RestHighLevelClient;

import com.strategyopr.util.StringTuple2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HbaseDemo {
    public static void main(String[] args) throws IOException {
        //1.通过用户输入关键词获取Es的bookID

    }
    public static String[] pullJsonFromHbase() throws IOException {
        HashMap infos = null;
        List jsonList =null ;
        RestHighLevelClient esConn = EsConnector.getConnection();
        List tuples = new ArrayList<StringTuple2>();
        StringTuple2 hostnamePro = new StringTuple2("hbase.zookeeper.quorum", "node1,node2,node3");
        tuples.add(hostnamePro);

        Connection hbaseConn = HbaseConnector.getConnection(tuples);
        boolean esIndexExist = EsDMLUtil.existsIndex(esConn,"book");
        Boolean hbaseTableExist = HbaseDMLUtil.existsTable(hbaseConn, "searchEngine", "book");
        if(!esIndexExist){
            EsDMLUtil.createIndex(esConn,"book");

        }
        if(!hbaseTableExist){
            HbaseDMLUtil.createTable(hbaseConn,"search_engine","book");
        }
        String[] ids = EsDemo.getIdsFromEs();
        jsonList = new ArrayList<String>();
        for (String id : ids) {
           infos = HbaseDDLUtil.getByRowKey(hbaseConn, "search_engine", "book", id);
            ObjectMapper oM = new ObjectMapper();
            String json = oM.writeValueAsString(infos);
            jsonList.add(json);
        }
        //2.把获取的对象元素HashMap转化为Json串给前端

        return (String[]) jsonList.toArray();
    }
}
