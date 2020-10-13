package br.com.marino.monitorar.shared;

import br.com.marino.monitorar.models.Column;
import br.com.marino.monitorar.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class TableModelCustom<Model> extends AbstractTableModel {

    private final List<Model> rows;
    private final List<Column> columns;

    public TableModelCustom(List<Column> columns) {
        this.columns = columns;
        this.rows = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getColumn();
    }

    public List<Model> getRows() {
        return rows;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Model obj = rows.get(rowIndex);

        try {            
            Utils.setValue(obj, aValue, Arrays.stream(columns.get(columnIndex)
                    .getField().split("\\.")).collect(Collectors.toList()));   
            fireTableRowsUpdated(rowIndex, rowIndex);
        } catch (Exception ex) {
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Model obj = rows.get(rowIndex);

        try {
            return Utils.getValue(obj, Arrays.stream(columns.get(columnIndex)
                    .getField().split("\\.")).collect(Collectors.toList()));            
        } catch (Exception ex) {
            return null;
        }

    }

    public void add(Model obj) {
        rows.add(obj);
        int lastIndice = getRowCount() - 1;
        fireTableRowsInserted(lastIndice, lastIndice);
    }

    public void remove(Model obj) {
        int row = rows.indexOf(obj);
        rows.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void addAll(List<Model> list) {
        if (list.isEmpty()) {
            return;
        }
        int indice = getRowCount();
        rows.addAll(list);
        fireTableRowsInserted(indice, indice + list.size() - 1);
    }

    public void clear() {
        rows.clear();
        fireTableDataChanged();
    }

    public void update(Model to, Model from) {
        int row = rows.indexOf(to);
        rows.set(row, from);
        fireTableRowsUpdated(row, row);
    }

}
