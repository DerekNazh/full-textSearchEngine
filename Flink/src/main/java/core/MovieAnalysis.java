package core;

import conn.FlinkConnector;
import org.apache.flink.streaming.api.scala.DataStream;
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;

public class MovieAnalysis {
    /** 数据格式
     * ID,Movie_Name_EN,Movie_Name_CN,Crawl_Date,Number,Username,Date,Star,Comment,Like
     * 0,Avengers Age of Ultron,复仇者联盟2,2017-01-22,1,然潘,2015-05-13,3, 连奥创都知道整容要去韩国。,2404
     */
    public static void main(String[] args) {
        StreamExecutionEnvironment conn = FlinkConnector.getConnection();
        DataStream<String> ds = conn.readTextFile("tiny_Moviesdata.csv");

    }
}
