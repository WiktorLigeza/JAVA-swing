package com.company.GUI;

import com.company.Models.CustomTableModel;
import com.company.Models.ItemModel;

import java.util.List;

public class DisplayProductTable  extends CustomTableModel<ItemModel> {


    public DisplayProductTable () {
        super();
    }

    public DisplayProductTable(List<ItemModel> rows) {
        super(rows);
    }

    @Override
    protected Object getValueAt(ItemModel item, int columnIndex) {
        Object tmp;
        tmp = item.name;
        return tmp;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        return "Name";
    }
}