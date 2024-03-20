package com.strategyopr.dataimport.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * String数据输出到文件， 单线程爬虫
 *
 */
public class FileUtils {
    public static void downToFile(String content, String path) {
        if (content.length() == 0 || content == null) {
            System.out.println("content is null or empty");
            return;
        }
        if (path == null) {
            System.out.println("targetPath is null");
            return;
        }
        BufferedWriter red = null;
        try {
            red = new BufferedWriter(new FileWriter(path,true));
            red.write(content.toCharArray());
            red.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
