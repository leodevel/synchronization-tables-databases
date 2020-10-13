package br.com.marino.monitorar.services;

import br.com.marino.monitorar.models.DataSource;
import br.com.marino.monitorar.models.TableColumn;
import br.com.marino.monitorar.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataSourceService {

    private static DataSourceService instance;

    public static DataSourceService getInstance() {
        if (instance == null) {
            instance = new DataSourceService();
        }
        return instance;
    }

    public List<String> getTablesByDataSource(DataSource dataSource) throws Exception {

        List<String> tables = new ArrayList<>();

        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'";

        try (Connection con = dataSource.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }

        }

        return tables;

    }

    public List<TableColumn> getTableColumnsByDataSource(DataSource dataSource) throws Exception {

        List<TableColumn> columns = new ArrayList<>();

        String sql = "SELECT c.COLUMN_NAME, c.DATA_TYPE, PRIMARY_KEY = (SELECT COUNT(COLUMN_NAME) "
                + "FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE "
                + "WHERE OBJECTPROPERTY(OBJECT_ID(CONSTRAINT_SCHEMA + '.' + QUOTENAME(CONSTRAINT_NAME)), 'IsPrimaryKey') = 1 "
                + "AND TABLE_NAME = '" + dataSource.getTable() + "' AND COLUMN_NAME = c.COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS AS c WHERE c.TABLE_NAME = '" + dataSource.getTable() + "' ORDER BY c.ORDINAL_POSITION";

        try (Connection con = dataSource.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                TableColumn column = new TableColumn();
                column.setColumnName(rs.getString("COLUMN_NAME"));
                column.setColumnType(Utils.getColumnTypeEnum(rs.getString("DATA_TYPE")));
                column.setPrimaryKey(rs.getInt("PRIMARY_KEY") == 1);
                columns.add(column);
            }

        }

        return columns;

    }

}
