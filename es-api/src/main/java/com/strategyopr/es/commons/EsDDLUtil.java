package com.strategyopr.es.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strategyopr.es.conn.EsConnector;
import com.strategyopr.flink.bean.BookBean;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EsDDLUtil {

    public static List  query(RestHighLevelClient conn,String index,String field,String searchStr) throws IOException {
        SearchRequest sr = Requests.searchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, searchStr);
         sourceBuilder.query(matchQueryBuilder);
         sr.source(sourceBuilder);
        SearchResponse search = conn.search(sr, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        List ints = new ArrayList<>();

        for (SearchHit hit : hits) {

                ints.add(hit.getId());
        }
        return ints;
    }
    public static IndexResponse insetRow(RestHighLevelClient conn,String index,String id,Object value) throws IOException {

        IndexRequest indexReq = new IndexRequest(index);
        indexReq.id(id);

        ObjectMapper oM = new ObjectMapper();
        String s1 = oM.writeValueAsString(value);
        indexReq.source(s1, XContentType.JSON);
        IndexResponse res = conn.index(indexReq, RequestOptions.DEFAULT);
        return res;
    }
}
