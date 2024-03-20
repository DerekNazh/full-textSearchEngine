package com.strategyopr.es.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.strategyopr.flink.bean.BookBean;
import com.strategyopr.es.conn.EsConnector;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class esDemo {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient conn = EsConnector.getConnection();
        IndexRequest indexReq = new IndexRequest("book");
        indexReq.id("10010");
        BookBean bookBean = new BookBean("1010", "a", "b", "c", "d", "f"
                , "e", "r", "q", "ss");

        ObjectMapper oM = new ObjectMapper();
        String s1 = oM.writeValueAsString(bookBean);
          indexReq.source(s1, XContentType.JSON);
        IndexResponse res = conn.index(indexReq, RequestOptions.DEFAULT);
        System.out.println(res);
        conn.close();


    }
}
