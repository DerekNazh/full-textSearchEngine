package com.strategyopr.es.contorller;

import com.strategyopr.es.conn.EsConnector;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsSearchHbase {
    public static void main(String[] args) {

    }
    //仅仅针对book数据。传入book索引，返回关键词在book中的倒排索引
    public String IDfromBookSearch(String keyword) throws IOException {
      String bookIndices= "book";
        String [] fields = {"ID","Movie_Name_EN","Movie_Name_CN","Username","Comment"};
        SearchResponse res = SearchFromEs(keyword, fields, bookIndices);

        SearchHits hits = res.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields.getId());
        }

        return "";
    }
    protected static SearchResponse SearchFromEs(String keyword,String[] fields,String indices) throws IOException {
        RestHighLevelClient conn = EsConnector.getConnection();
        SearchRequest searreq = Requests.searchRequest(indices);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        for (String field : fields) {
            sourceBuilder.query(new FuzzyQueryBuilder(field, keyword));
        }
         searreq.source(sourceBuilder);
        return conn.search(searreq, RequestOptions.DEFAULT);

    }
}
