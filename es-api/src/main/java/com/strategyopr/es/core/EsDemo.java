package com.strategyopr.es.core;


import com.strategyopr.es.commons.EsDDLUtil;
import com.strategyopr.es.conn.EsConnector;
import org.elasticsearch.client.RestHighLevelClient;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class EsDemo {

    public static String[] getIdsFromEs() throws IOException {
        RestHighLevelClient conn = EsConnector.getConnection();
        HashSet<Integer> idsFromEs = getIdsFromEs(conn, "book", "米");
        String[] array = (String[]) idsFromEs.toArray();
        EsConnector.close();
        return array;

    }

    /**
     * 指定索引库，关键词所搜所有属性中的相关数据的ids
     * @param conn  链接对象
     * @param indices  索引库
     * @param searchStr  搜索关键词
     * @return
     * @throws IOException
     */
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