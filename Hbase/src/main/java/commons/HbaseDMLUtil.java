package commons;

import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.util.Bytes;
import util.StringTuple2;

import java.io.IOException;
import java.util.ArrayList;

public class HbaseDMLUtil {

    //创建命令空间
    public static Boolean createNameSpace(Connection conn, String namespace, ArrayList<StringTuple2>  properties) throws IOException {
        if(properties.size() == 0 ||properties ==null){
            createNameSpace(conn,namespace);
            return true;
        }
        Admin admin = conn.getAdmin();
        NamespaceDescriptor.Builder ndBuilder = NamespaceDescriptor.create(namespace);
        for (StringTuple2 property : properties) {
            ndBuilder.addConfiguration(property.getK(),property.getV());
        }
        admin.createNamespace(ndBuilder.build());
        return true;
    }
    public  static  Boolean createNameSpace(Connection conn,String namespace) throws IOException {
        Admin admin = conn.getAdmin();
        NamespaceDescriptor.Builder ndBuilder = NamespaceDescriptor.create(namespace);
        admin.createNamespace(ndBuilder.build());
        return true;
    }
    //创建表
    public static Boolean createTable(Connection conn,String namespace,String table,ArrayList<StringTuple2>  tlbProperties) throws IOException {
        System.out.println("通用型创表，尚未开发");
       return false;
    }
    public static Boolean createTable(Connection conn,String nameSpace,String table,String... columnFamily) throws IOException {
        Admin admin = conn.getAdmin();

        TableDescriptorBuilder tlbBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(nameSpace, table));
        for (String s : columnFamily) {
           tlbBuilder.addColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(s)).build());
        }

                admin.createTable(tlbBuilder.build());
        return true;
    }
    public static Boolean existsTable(Connection conn,String namespace,String table) throws IOException {
        Admin admin = conn.getAdmin();
        boolean b = admin.tableExists(TableName.valueOf(namespace, table));
        return true;
    }
        //列出所有命名空间
    public static String[] NamespaceLists(Connection conn) throws IOException {
        Admin admin = conn.getAdmin();
        String[]  lists = admin.listNamespaces();
        return lists;
    }
        //删除表
    public static Boolean deleteTable(Connection conn,String namespace,String table) throws IOException {
        Admin admin = conn.getAdmin();
        admin.deleteTable(TableName.valueOf(namespace,table));
        return true;
    }
        //更改表
    public static Boolean ModifyTable(Connection conn,String nameSpace,String table) throws IOException {
        System.out.println("准备用一些枚举来做列簇属性，尚未开发");
        return false;
    }


}
