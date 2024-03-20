package com.strategyopr.es.commons;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

import java.io.IOException;

public class EsDMLUtil {

    public static boolean createIndex(RestHighLevelClient conn, String indexName) throws IOException {
        IndicesClient indexManager = conn.indices();
        CreateIndexRequest indexRequest = Requests.createIndexRequest(indexName);
        CreateIndexResponse res = indexManager.create(indexRequest, RequestOptions.DEFAULT);
        return res.isAcknowledged();
    }
    public static boolean existsIndex(RestHighLevelClient conn, String indexName) throws IOException {
        IndicesClient indexManager = conn.indices();
        GetIndexRequest getIndexReq = new GetIndexRequest(indexName);
        return indexManager.exists(getIndexReq, RequestOptions.DEFAULT);
    }
    public boolean listIndices() {

        return true;
    }

    public boolean getIndexInfo() {
        return true;
    }
}
