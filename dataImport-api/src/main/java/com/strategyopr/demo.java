package com.strategyopr;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class demo {
    public static void main(String[] args) throws IOException {
        //获取客户端，浏览器代理对象
        CloseableHttpClient cli = HttpClients.createDefault();
        //创建请求get 或 post
        String url = "https://fanyi.baidu.com/sug";
        HttpPost post = new HttpPost(url);
        ArrayList<NameValuePair> prop = new ArrayList<>();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(prop,"utf8");
            post.setEntity(entity);

        //发送请求，获取响应结果
        CloseableHttpResponse res = cli.execute(post);

        //对结果信息进行处理
        if(res.getStatusLine().getStatusCode() == 200){
            HttpEntity entity1 = res.getEntity();
            String string = EntityUtils.toString(entity1,"UTF8");
        }
        //关闭资源
        res.close();
        cli.close();
    }
}
