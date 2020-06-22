package com.company.Models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomTableModel<T> extends AbstractTableModel {
    protected List<T> rows;

    public CustomTableModel() {
        rows = new ArrayList<>();
    }

    public CustomTableModel(List<T> rows) {
        this.rows = rows;
    }

    void addRow(T t) {
        this.rows.add(t);
        fireTableDataChanged();
    }

    void addRows(List<T> rows) {
        this.rows.addAll(rows);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAt(rows.get(rowIndex), columnIndex);
    }

    protected abstract Object getValueAt(T t, int columnIndex);

    @Override
    public abstract String getColumnName(int column);
}