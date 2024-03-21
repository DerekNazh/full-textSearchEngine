package com.strategyopr.es.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.strategyopr.es.commons.EsDDLUtil;
import com.strategyopr.es.conn.EsConnector;
import org.elasticsearch.client.RestHighLevelClient;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class esDemo {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient conn = EsConnector.getConnection();
        HashSet<Integer> idsFromEs = getIdsFromEs(conn, "book", "米");
        Object[] array = idsFromEs.toArray();
        System.out.println(array.length);
        for (Object o : array) {
            System.out.println(o);
        }
        EsConnector.close();


    }

    public static HashSet<Integer> getIdsFromEs(RestHighLevelClient conn, String indices, String searchStr) throws IOException {
        HashSet<Integer> set = new HashSet<>();
        List ints1 = EsDDLUtil.query(conn, indices, "comment", "米");
        List ints2 = EsDDLUtil.query(conn, indices, "comment", "米");
        List ints3 = EsDDLUtil.query(conn, indices, "comment", "米");
        List ints4 = EsDDLUtil.query(conn, indices, "comment", "米");
        List ints5 = EsDDLUtil.query(conn, indices, "comment", "米");
           set.addAll(ints1);
           set.addAll(ints2);
           set.addAll(ints3);
           set.addAll(ints4);
           set.addAll(ints5);
           return set;
    }
}