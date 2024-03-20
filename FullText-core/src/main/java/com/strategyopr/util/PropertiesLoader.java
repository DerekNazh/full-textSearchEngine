package com.strategyopr.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public  static Properties load(String proPath){
        Properties pro = new Properties();
        InputStream is = PropertiesLoader.class.getClassLoader().getResourceAsStream(proPath);
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro;
    }
}
