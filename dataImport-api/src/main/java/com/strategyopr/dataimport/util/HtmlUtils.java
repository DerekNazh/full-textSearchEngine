package com.strategyopr.dataimport.util;

import com.strategyopr.dataimport.conn.HttpClientsConnector;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * getHtmlFromURL   #获取String类型的html静态元素
 */
public class HtmlUtils {
    public static boolean download(String url,String path){
        //把String数据写入文件
           FileUtils.downToFile(getHtmlFromURL(url),path);
        return true;
    }
    /**
     * 使用无参数got请求
     * 获取String类型的html静态元素
     * @param url
     * @return
     */
    public static String getHtmlFromURL(String url){
        //从连接池中获取连接
        CloseableHttpClient cli = new HttpClientsConnector().getCli();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse res = null;
        try {
            res = cli.execute(get);
        HttpEntity entity = res.getEntity();
            res.close();
          String  content = EntityUtils.toString(entity, "UTF8");
          return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
