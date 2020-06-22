package com.company.GUI;

import com.company.Models.CustomTableModel;
import com.company.Models.ItemModel;

import java.util.List;

public class DisplayProductInfoTable  extends CustomTableModel<ItemModel> {


    public DisplayProductInfoTable () {
        super();
    }

    public DisplayProductInfoTable (List<ItemModel> rows) {
        super(rows);
    }

    @Override
    protected Object getValueAt(ItemModel item, int columnIndex) {
        Object tmp;
        if (columnIndex == 0) {
            if(item.name.equals("select center")){tmp = "Name";}
            else tmp = item.name;
        }
        else  if (columnIndex == 1) {
            if(item.name.equals("select center")){tmp = "State";}
            else tmp = item.getState().toString();
        }
        else {
            if(item.name.equals("select center")){tmp = "Weight";}
            else tmp = item.weight;
        }
        return tmp;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0 : return "Name";
            case 1 : return "State";
            default: return "Weight";
        }
    }
}
