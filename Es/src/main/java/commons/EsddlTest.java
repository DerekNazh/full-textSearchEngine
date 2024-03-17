package commons;

import conn.EsConnector;
import util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

public class EsddlTest {
    public static void main(String[] args) throws IOException {
        boolean b = EsDMLUtil.existsIndex(EsConnector.getConnection(), "indextest111");
        System.out.println(b);
    }
}
