---
title: HBase API
date: 2019-02-13 22:39:13
tags:
---

### 利用HBase的JavaApi进行简单的CRUD

```java
package com.iceberg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

public class HBaseConn {
    private static final HBaseConn INSTANCE  = new HBaseConn();
    private static Connection connection;
    private static Configuration configuration;

    private HBaseConn() {
        try {
            if (configuration == null) {
                configuration = HBaseConfiguration.create();
                configuration.set("hbase.zookeeper.quorum", "192.168.3.128:2181");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        if (connection==null || connection.isClosed()) {
            try {
                connection = ConnectionFactory.createConnection(configuration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Connection getHBaseCoon() {
        return INSTANCE.getConnection();
    }

    public static Table getTable(String tableName) throws IOException {
        return INSTANCE.getConnection().getTable(TableName.valueOf(tableName));
    }

    public static void closeConn() {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
```

```java
package com.iceberg;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class HBaseUtil {
    /**
     * create a HBase table
     * @param tableName name of table
     * @param cfs an array of column family
     * @return if created success return true
     */
    public static boolean createTable(String tableName, String[] cfs) {
        try {
            HBaseAdmin admin = (HBaseAdmin)HBaseConn.getHBaseCoon().getAdmin();
            if (admin.tableExists(tableName)) {
                return false;
            }
            final HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            for (String cf: cfs) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
                hColumnDescriptor.setMaxVersions(1);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
//            Arrays.stream(cfs).forEach(cf -> {
//                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
//                hColumnDescriptor.setMaxVersions(1);
//                hTableDescriptor.addFamily(hColumnDescriptor);
//            });
            admin.createTable(hTableDescriptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * delete table in HBase
     * @param tableName name of table
     * @return true if delete success
     */
    public static boolean deleteTable(String tableName) {
        try (HBaseAdmin admin = (HBaseAdmin)HBaseConn.getHBaseCoon().getAdmin()){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * insert one data in HBase
     * @param tableName
     * @param rowKey
     * @param cfName
     * @param qualifier
     * @param data
     * @return
     */
    public static boolean putRow(String tableName, String rowKey, String cfName, String qualifier, String data) {
        try (Table table = HBaseConn.getTable(tableName)){
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(cfName), Bytes.toBytes(qualifier), Bytes.toBytes(data));
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * insert batch data in HBase
     * @param tableName
     * @param puts
     * @return
     */
    public static boolean putRows(String tableName, List<Put> puts) {
        try (Table table = HBaseConn.getTable(tableName)){
            table.put(puts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * no filter
     * @param tableName
     * @param rowKey
     * @return
     */
    public static Result getRow(String tableName, String rowKey) {
        try (Table table = HBaseConn.getTable(tableName)){
            Get get = new Get(Bytes.toBytes(rowKey));
            return table.get(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * with filter
     * @param tableName
     * @param rowKey
     * @param filterList
     * @return
     */
    public static Result getRow(String tableName, String rowKey, FilterList filterList) {
        try (Table table = HBaseConn.getTable(tableName)){
            Get get = new Get(Bytes.toBytes(rowKey));
            get.setFilter(filterList);
            return table.get(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * scan the whole table
     * @param tableName
     * @return
     */
    public static ResultScanner getScanner(String tableName) {
        try (Table table = HBaseConn.getTable(tableName)) {
            Scan scan = new Scan();
            scan.setCaching(1000);
            return table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * scan with start and end
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @return
     */
    public static ResultScanner getScanner(String tableName, String startRowKey, String stopRowKey) {
        try (Table table = HBaseConn.getTable(tableName)) {
            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes(startRowKey));
            scan.setStopRow(Bytes.toBytes(stopRowKey));
            scan.setCaching(1000);
            return table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * scan with start and end and filter
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @param filterList
     * @return
     */
    public static ResultScanner getScanner(String tableName, String startRowKey, String stopRowKey, FilterList filterList) {
        try (Table table = HBaseConn.getTable(tableName)) {
            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes(startRowKey));
            scan.setStopRow(Bytes.toBytes(stopRowKey));
            scan.setFilter(filterList);
            scan.setCaching(1000);
            return table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * delete one row
     * @param tableName
     * @param rowKey
     * @return
     */
    public static boolean deleteRow(String tableName, String rowKey) {
        try (Table table = HBaseConn.getTable(tableName)) {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deleteColumnFamily(String tableName, String cfName) {
        try (HBaseAdmin admin = (HBaseAdmin)HBaseConn.getHBaseCoon().getAdmin()){
            admin.deleteColumn(tableName, cfName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deleteQualifier(String tableName, String rowKey, String cfName, String qualifier) {
        try (Table table = HBaseConn.getTable(tableName)) {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumn(Bytes.toBytes(cfName), Bytes.toBytes(qualifier));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
```

```java
package com.iceberg;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.util.Arrays;


public class HBaseUtilTest {

    @Test
    public void createTable() {
        boolean result = HBaseUtil.createTable("FileTable", new String[]{"fileInfo", "saveInfo"});
        System.out.println(result);
    }

    @Test
    public void deleteTable() {
        HBaseUtil.deleteTable("TestTable");
    }

    @Test
    public void putRow() {
        HBaseUtil.putRow("FileTable", "rowkey1", "fileInfo", "name", "file1.txt");
        HBaseUtil.putRow("FileTable", "rowkey1", "fileInfo", "type", "txt");
        HBaseUtil.putRow("FileTable", "rowkey1", "fileInfo", "size", "1024");
        HBaseUtil.putRow("FileTable", "rowkey1", "saveInfo", "creator", "superman");

        HBaseUtil.putRow("FileTable", "rowkey2", "fileInfo", "name", "file2.txt");
        HBaseUtil.putRow("FileTable", "rowkey2", "fileInfo", "type", "txt");
        HBaseUtil.putRow("FileTable", "rowkey2", "fileInfo", "size", "2048");
        HBaseUtil.putRow("FileTable", "rowkey2", "saveInfo", "creator", "batman");

        HBaseUtil.putRow("FileTable", "rowkey3", "fileInfo", "name", "file3.txt");
        HBaseUtil.putRow("FileTable", "rowkey3", "fileInfo", "type", "txt");
        HBaseUtil.putRow("FileTable", "rowkey3", "fileInfo", "size", "3072");
        HBaseUtil.putRow("FileTable", "rowkey3", "saveInfo", "creator", "wonder woman");
    }

    @Test
    public void putRows() {
    }

    @Test
    public void getRow() {
        Result result = HBaseUtil.getRow("FileTable", "rowkey1");
        if (result != null) {
            System.out.println("rowkey=" + Bytes.toString(result.getRow()));
            System.out.println("rowkey=" + Bytes.toString(result.getValue(Bytes.toBytes("fileInfo"), Bytes.toBytes("name"))));
        }
    }

    @Test
    public void getRow1() {
    }

    @Test
    public void getScanner() {
        ResultScanner scanner = HBaseUtil.getScanner("FileTable");
        printScannerResult(scanner);
    }

    private void printScannerResult(ResultScanner scanner) {
        if (scanner != null) {
            scanner.forEach(result -> {
                System.out.println("rowkey=" + Bytes.toString(result.getRow()));
                System.out.println("rowkey=" + Bytes.toString(result.getValue(Bytes.toBytes("fileInfo"), Bytes.toBytes("name"))));
            });
            scanner.close();
        }else {
            System.out.println("None data found");
        }
    }
    
    @Test
    public void getScanner1() {
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("rowkey1")));

        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL, Arrays.asList(filter));

        ResultScanner scanner = HBaseUtil.getScanner("FileTable", "rowkey1", "rowkey2", filterList);

        printScannerResult(scanner);

    }

    @Test
    public void getScanner2() {
    }

    @Test
    public void deleteRow() {
        HBaseUtil.deleteRow("FileTable", "rowkey1");
    }

    @Test
    public void deleteColumnFamily() {
    }

    @Test
    public void deleteQualifier() {
    }
}
```