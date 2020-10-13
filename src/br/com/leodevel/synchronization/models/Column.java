package br.com.leodevel.synchronization.models;

public class Column {

    private String column;
    private String field;
    private Class type;
    private boolean editable;

    public Column(String column, String field, Class type) {
        this.column = column;
        this.field = field;
        this.type = type;
        this.editable = false;
    }
    
    public Column(String column, String field, Class type, boolean editable) {
        this.column = column;
        this.field = field;
        this.type = type;
        this.editable = editable;
    }

    public Column() {
    }
    
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }    

}
