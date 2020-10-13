package br.com.leodevel.synchronization.services;

import br.com.leodevel.synchronization.enums.ColumnTypeEnum;
import br.com.leodevel.synchronization.models.Column;
import br.com.leodevel.synchronization.models.DataMapping;
import br.com.leodevel.synchronization.models.DataSynchronize;
import br.com.leodevel.synchronization.models.TableColumn;
import br.com.leodevel.synchronization.shared.CrudService;
import br.com.leodevel.synchronization.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataSynchronizeService extends CrudService<DataSynchronize> {

    private static DataSynchronizeService instance;

    public static DataSynchronizeService getInstance() {
        if (instance == null) {
            instance = new DataSynchronizeService();
        }
        return instance;
    }

    @Override
    public int getPosition(List<DataSynchronize> list, DataSynchronize obj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().intValue() == obj.getId().intValue()) {
                return i;
            }
        }
        return 0;
    }

    public DataSynchronize getById(int id) {
        try {
            return getAll().stream().filter(ds -> ds.getId() == id).findFirst().orElse(null);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void insert(DataSynchronize obj) throws Exception {
        List<DataSynchronize> list = getAll();
        obj.setId(getId(list));
        list.add(obj);
        save(list);
    }

    @Override
    public void delete(DataSynchronize obj) throws Exception {
        List<DataSynchronize> list = getAll();
        list.remove(getPosition(list, obj));
        save(list);
    }

    private int getId(List<DataSynchronize> list) {
        int newId = 0;
        for (DataSynchronize a : list) {
            if (newId < a.getId()) {
                newId = a.getId();
            }
        }
        return (newId + 1);
    }

    @Override
    public List<Column> getColumns() {
        List<Column> columns = Arrays.asList();
        return columns;
    }

    public String getSelectSqlSource(DataSynchronize dataSynchronize) {

        String where = "";
        String order = "";
        String limit = "";

        // qtd a ser reprocessada
        if (!dataSynchronize.getProcessAll()) {

            List<TableColumn> primaryKeys = dataSynchronize
                    .getAllColumnsSource()
                    .stream()
                    .filter(dm -> dm.getPrimaryKey())
                    .collect(Collectors.toList());

            if (primaryKeys.size() == 1) {
                order = "ORDER BY " + primaryKeys.get(0).getColumnName() + " ASC";
                if (dataSynchronize.getLastReading() != null) {
                    where = "WHERE " + primaryKeys.get(0).getColumnName() + " > ?";
                }
            }

        }

        return "SELECT " + limit + " * FROM " + dataSynchronize.getSource().getTable() + " " + where + " " + order;

    }

    public String getInsertSqlDestination(DataSynchronize dataSynchronize) {

        String fields = String.join(",", dataSynchronize
                .getDataMapping()
                .stream()
                .filter(dm -> dm.getColumnDestination() != null)
                .map(dm -> dm.getColumnDestination().getColumnName())
                .toArray(String[]::new));

        String values = "";
        for (int i = 0; i < dataSynchronize.getDataMapping().stream().filter(dm -> dm.getColumnDestination() != null).count(); i++) {
            values = (values.isEmpty() ? "?" : values + ",?");
        }

        String sql = "INSERT INTO " + dataSynchronize.getDestination().getTable() + " (" + fields + ") VALUES (" + values + ")";

        return sql;
    }

    public String getUpdateSqlDestination(DataSynchronize dataSynchronize) {

        String fields = "";

        List<String> columns = dataSynchronize
                .getDataMapping()
                .stream().filter(dm -> dm.getColumnDestination() != null)
                .map(dm -> dm.getColumnDestination().getColumnName())
                .collect(Collectors.toList());

        for (String column : columns) {
            fields = (fields.isEmpty() ? (column + "=?") : (fields + "," + column + "=?"));

        }

        TableColumn primaryKey = dataSynchronize.getAllColumnsDestination().stream()
                .filter(c -> c.getPrimaryKey()).findFirst().get();

        return "UPDATE " + dataSynchronize.getDestination().getTable() + " SET " + fields + " WHERE " + primaryKey.getColumnName() + "=?";

    }

    public List<Map<String, Object>> getSelectBySource(DataSynchronize dataSynchronize) throws Exception {

        List<Map<String, Object>> list = new ArrayList<>();
        String sql = getSelectSqlSource(dataSynchronize);

        try (Connection con = dataSynchronize.getSource().getConnection();
                PreparedStatement prep = con.prepareStatement(sql)) {

            if (!dataSynchronize.getProcessAll()) {
                List<TableColumn> primaryKeys = dataSynchronize
                        .getAllColumnsSource()
                        .stream()
                        .filter(dm -> dm.getPrimaryKey())
                        .collect(Collectors.toList());
                if (primaryKeys.size() == 1) {
                    if (dataSynchronize.getLastReading() != null) {
                        prep.setObject(1, dataSynchronize.getLastReading());
                    }
                }
            }

            try (ResultSet res = prep.executeQuery()) {

                while (res.next()) {
                    
                    Map<String, Object> row = new HashMap<>();
                    
                    dataSynchronize
                            .getAllColumnsSource()
                            .stream()
                            .forEach(c -> {
                                try {
                                    row.put(c.getColumnName(), getValue(res, c));
                                } catch (Exception ex) {
                                }
                            });
                    
                    list.add(row);
                    
                }
                
            }

        }

        return list;

    }

    public Object insertToDestination(DataSynchronize dataSynchronize, Map<String, Object> row) throws Exception {

        String sql = getInsertSqlDestination(dataSynchronize);

        try (Connection con = dataSynchronize.getSource().getConnection();
                PreparedStatement prep = con.prepareStatement(sql)) {

            List<DataMapping> dataMapping = dataSynchronize
                    .getDataMapping()
                    .stream()
                    .filter(dm -> dm.getColumnDestination() != null)
                    .collect(Collectors.toList());

            for (int i = 0; i < dataMapping.size(); i++) {
                if (Utils.isNullOrEmpty(dataMapping.get(i).getValueDefault())){
                    prep.setObject((i + 1), row.get(dataMapping.get(i).getColumnSource().getColumnName()));
                }else{
                    prep.setObject((i + 1), getValueDefault(dataMapping.get(i)));
                }                
            }

            prep.executeUpdate();

        }

        return row.entrySet().stream().filter(r -> r.getKey().equalsIgnoreCase(
                dataSynchronize.getAllColumnsSource().stream().filter(c -> c.getPrimaryKey()).findFirst().get().getColumnName())
        ).findFirst().get().getValue();

    }

    public Object updateToDestination(DataSynchronize dataSynchronize, Map<String, Object> row) throws Exception {

        String sql = getUpdateSqlDestination(dataSynchronize);

        TableColumn primaryKey = dataSynchronize.getAllColumnsSource().stream()
                .filter(c -> c.getPrimaryKey()).findFirst().get();

        try (Connection con = dataSynchronize.getSource().getConnection();
                PreparedStatement prep = con.prepareStatement(sql)) {

            List<DataMapping> dataMapping = dataSynchronize
                    .getDataMapping()
                    .stream()
                    .filter(dm -> dm.getColumnDestination() != null)
                    .collect(Collectors.toList());

            for (int i = 0; i < dataMapping.size(); i++) {
                if (Utils.isNullOrEmpty(dataMapping.get(i).getValueDefault())){
                    prep.setObject((i + 1), row.get(dataMapping.get(i).getColumnSource().getColumnName()));
                }else{
                    prep.setObject((i + 1), getValueDefault(dataMapping.get(i)));
                }                                
            }

            prep.setObject(dataMapping.size() + 1, row.get(primaryKey.getColumnName()));

            prep.executeUpdate();

        }

        return row.entrySet().stream().filter(r -> r.getKey().equalsIgnoreCase(
                primaryKey.getColumnName())
        ).findFirst().get().getValue();

    }

    private Object getValue(ResultSet res, TableColumn tableColumn) throws SQLException {

        if (tableColumn.getColumnType() == ColumnTypeEnum.TEXT) {
            return res.getString(tableColumn.getColumnName());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.BOOLEAN) {
            return res.getBoolean(tableColumn.getColumnName());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.DOUBLE) {
            return res.getDouble(tableColumn.getColumnName());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.FLOAT) {
            return res.getFloat(tableColumn.getColumnName());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.DATETIME) {
            return res.getTimestamp(tableColumn.getColumnName());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.INTEGER) {
            return res.getInt(tableColumn.getColumnName());
        }

        throw new SQLException();

    }
    
    public Object getValueDefault(DataMapping dataMapping) throws SQLException {
        
        TableColumn tableColumn = dataMapping.getColumnDestination();
        
        if (tableColumn.getColumnType() == ColumnTypeEnum.TEXT) {
            return dataMapping.getValueDefault().toString();
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.BOOLEAN) {
            if (dataMapping.getValueDefault().toString().equalsIgnoreCase("true") || 
                    dataMapping.getValueDefault().toString().equalsIgnoreCase("1")){
                return Boolean.TRUE;
            }
            if (dataMapping.getValueDefault().toString().equalsIgnoreCase("false") ||
                    dataMapping.getValueDefault().toString().equalsIgnoreCase("0")){
                return Boolean.FALSE;
            }
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.DOUBLE) {
            return Double.parseDouble(dataMapping.getValueDefault().toString());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.FLOAT) {
            return Float.parseFloat(dataMapping.getValueDefault().toString());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.DATETIME) {
            return new Date(dataMapping.getValueDefault().toString());
        }

        if (tableColumn.getColumnType() == ColumnTypeEnum.INTEGER) {
            return Integer.parseInt(dataMapping.getValueDefault().toString());
        }

        throw new SQLException();
        
    }

}
