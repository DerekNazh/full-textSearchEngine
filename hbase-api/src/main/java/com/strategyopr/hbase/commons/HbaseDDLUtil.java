package com.strategyopr.hbase.commons;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.com.google.protobuf.Any;

import com.strategyopr.util.ReflectManager;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;


//插入数据
public class HbaseDDLUtil {
    public static Boolean insertCell(Connection conn,String namespace,String tableName,String rowKey,String columnFamily,String columnName, String value) throws IOException {
        if(HbaseDMLUtil.existsTable(conn,namespace,tableName) == false){
            System.out.println("指定的表不存在，未创建");
            return false;
        }
            Table table = conn.getTable(TableName.valueOf(namespace, tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(columnName),Bytes.toBytes(value));
        table.put(put);
        return true;
    }
    //把对象的所有属性值插入到一个指定列簇中
    public static Boolean insertObj(Connection conn,String namespace,String tableName,String rowKey,String columnFamily,Object o) throws IOException, NoSuchMethodException {
        if(HbaseDMLUtil.existsTable(conn,namespace,tableName) == false){
            System.out.println("指定的表不存在，未创建");
            return false;
        }
        Table table = conn.getTable(TableName.valueOf(namespace, tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        //获取对象的属性String数组
        try {
            String[] fields = ReflectManager.getFieldsArray(o);
            Object[] values = ReflectManager.getValuesArray(o);
            for (int i = 0; i < fields.length; i++) {

                put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(fields[i]),
                              Bytes.toBytes((String)values[i]));
                table.put(put);
            }
        } catch (Exception e) {
            System.out.println("没有设置get，set方法");
            e.printStackTrace();
        }

        return true;
    }
    public static void insertMap(HashMap map){};
    //查询数据
    //按rowkey读取1行
    public static HashMap getByRowKey(Connection conn,String namespace,String tableName,String rowKey) throws IOException {
        Table table = conn.getTable(TableName.valueOf(Bytes.toBytes(tableName),(Bytes.toBytes(namespace))));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        List<Cell> cells = result.listCells();
        HashMap<String, String> kvs = new HashMap<>();
        for (Cell cell : cells) {
            kvs.put(new String(CellUtil.cloneQualifier(cell)),new String(CellUtil.cloneValue(cell)));
        }
        return kvs;
    }
}
