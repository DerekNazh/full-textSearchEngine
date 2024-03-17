package conn;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

public class EsConnector {
    private static RestClientBuilder rcBuilder = null;
    private static RestHighLevelClient highCli = null;

    private String hostname = null;
    private String port = null;

    /**
     * properites加载参数方法创建
     * @return
     */
    public static RestHighLevelClient getConnection(){
        Properties pro = PropertiesLoader.load("es.properties");

        if(rcBuilder == null){
            rcBuilder = RestClient.builder(new HttpHost(pro.getProperty("hostname"),
                                        Integer.parseInt(pro.getProperty("port"))));
        }

        if(highCli == null){
            highCli = new RestHighLevelClient(rcBuilder);
        }
       return highCli;

    }
    /**
     * 传入形参型创建
     * @return
     */
    public static RestHighLevelClient getConnection(String hostname,int port){

        if(rcBuilder == null){
            rcBuilder = RestClient.builder(new HttpHost(hostname,port));
        }

        if(highCli == null){
            highCli = new RestHighLevelClient(rcBuilder);
        }
        return highCli;
    }
    //关闭资源
    public Boolean close() throws IOException {
        if(highCli !=null){
            highCli.close();
        }
        return true;
    }

}
