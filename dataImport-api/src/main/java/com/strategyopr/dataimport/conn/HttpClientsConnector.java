package com.strategyopr.dataimport.conn;

import com.strategyopr.dataimport.conn.inf.Connector;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientsConnector implements Connector {
    CloseableHttpClient cli = null;
    @Override
    public void setup() {

    }
    public HttpClientsConnector(){
        cli = HttpClients.custom().setUserAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36").build();
    }

    public CloseableHttpClient getCli() {
        return cli;
    }
    @Override
    public void close() {

    }
}
