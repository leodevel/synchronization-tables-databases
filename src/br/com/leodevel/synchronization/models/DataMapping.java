package br.com.leodevel.synchronization.models;

public class DataMapping {
    
    private TableColumn columnSource;
    private TableColumn columnDestination;        
    private Object valueDefault;

    public TableColumn getColumnSource() {
        return columnSource;
    }

    public void setColumnSource(TableColumn columnSource) {
        this.columnSource = columnSource;
    }

    public TableColumn getColumnDestination() {
        return columnDestination;
    }

    public void setColumnDestination(TableColumn columnDestination) {
        this.columnDestination = columnDestination;
    }

    public Object getValueDefault() {
        return valueDefault;
    }

    public void setValueDefault(Object valueDefault) {
        this.valueDefault = valueDefault;
    }        
    
}
