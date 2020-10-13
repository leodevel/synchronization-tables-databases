package br.com.leodevel.synchronization.models;

import br.com.leodevel.synchronization.enums.ColumnTypeEnum;

public class TableColumn {
    
    private String columnName;
    private ColumnTypeEnum columnType;
    private Boolean primaryKey;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ColumnTypeEnum getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnTypeEnum columnType) {
        this.columnType = columnType;
    }    

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    public TableColumn copy() {
        TableColumn tc = new TableColumn();
        tc.setColumnName(columnName);
        tc.setColumnType(columnType);
        tc.setPrimaryKey(primaryKey);       
        return tc;
    }
    
    @Override
    public String toString() {
        return columnName;
    }    
    
}
