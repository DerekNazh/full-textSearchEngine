package com.strategyopr.hbase.conn;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import com.strategyopr.util.StringTuple2;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


import java.io.IOException;
import java.util.ArrayList;

public class HbaseConnector {
   private static Connection conn =null;
    private HbaseConnector(){}
    public static Connection getConnection(ArrayList<StringTuple2> tuples) throws IOException {
        if(conn == null){
            Configuration conf = HBaseConfiguration.create();
            for (int i = 0; i < tuples.size(); i++) {
                conf.set((String) tuples.get(i).getK(), (String) tuples.get(i).getK());
                conn = ConnectionFactory.createConnection(conf);
            }
        }
        return conn;
    }
    public static Boolean close() throws IOException {
         conn.close();
        return true;
    }
}
