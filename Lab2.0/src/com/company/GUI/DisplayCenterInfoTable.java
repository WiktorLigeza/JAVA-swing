package com.company.GUI;

import com.company.Models.CustomTableModel;
import com.company.Models.FulfillmentCenterModel;

import javax.swing.event.TableModelListener;
import java.util.List;

public class DisplayCenterInfoTable extends CustomTableModel<FulfillmentCenterModel> {


    public DisplayCenterInfoTable () {
        super();
    }

    public DisplayCenterInfoTable (List<FulfillmentCenterModel> rows) {
        super(rows);
    }

    @Override
    protected Object getValueAt(FulfillmentCenterModel center, int columnIndex) {
        Object tmp;
        if (columnIndex == 0) {
            if(center.name.equals("")){tmp = "Name";}
            else tmp = center.name;
        }
        else  if (columnIndex == 1) {
            if(center.maximumCapacity == 0){tmp = "Max capacity";}
            else tmp = center.maximumCapacity;
        }
        else if (columnIndex == 2) {

            if(center.name.equals("")){tmp = "Number of products";}
            else tmp = center.itemsList.size();
        }
        else {
            if(center.maximumCapacity == 0){tmp = "Fill level";}
            else {
                String s = String.valueOf((float)center.fillLvl());
                tmp = s+"%";}
        }
        return tmp;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0 : return "Name";
            case 1 : return "Max capacity";
            case 2 : return "Number of Elements";
            default: return "Fill Level";
        }
    }
}
