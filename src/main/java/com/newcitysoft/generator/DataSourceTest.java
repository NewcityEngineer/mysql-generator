package com.newcitysoft.generator;

import com.alibaba.fastjson.JSONObject;
import com.newcitysoft.generator.core.TableMeta;
import com.newcitysoft.generator.kit.StrKit;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/8/30 18:05
 */
public class DataSourceTest {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = DataSourceUtil.getDataSource();

        Connection connection = dataSource.getConnection();

        ConnPrint print = new ConnPrint(connection);

        print.print();
    }

    private static class ConnPrint {
        private Connection connection;

        public ConnPrint(Connection connection) {
            this.connection = connection;
        }

        public void print() throws SQLException {
            printCatalog();
            printClientInfo();
            printMetaData();
        }

        private void printCatalog() throws SQLException {
            System.out.println("catalog:"+ this.connection.getCatalog());
        }

        private void printClientInfo() throws SQLException {
            Properties clientInfo = this.connection.getClientInfo();

            System.out.println(JSONObject.toJSONString(clientInfo));
        }

        private void printMetaData() throws SQLException {
            DatabaseMetaData metaData = this.connection.getMetaData();

            ResultSet rs = metaData.getTables(this.connection.getCatalog(),
                    null,
                    null,
                    new String[]{"TABLE"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");

                TableMeta tableMeta = new TableMeta();
                tableMeta.name = tableName;
                tableMeta.remarks = rs.getString("REMARKS");
                tableMeta.modelName = buildModelName(tableName);

                System.out.println(JSONObject.toJSONString(tableMeta));
            }

            rs.close();
        }

        private String buildModelName(String tableName) {
            return StrKit.firstCharToUpperCase(StrKit.toCamelCase(tableName));
        }

        /**
         * 使用 modelName 构建 baseModelName
         */
        private String buildBaseModelName(String modelName) {
            return "Base" + modelName;
        }

    }
}
