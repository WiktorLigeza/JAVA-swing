package com.company.GUI;

import java.util.List;
import com.company.Models.CustomTableModel;
import com.company.Models.FulfillmentCenterModel;

import javax.swing.event.TableModelListener;

public class DisplayCenterTable extends CustomTableModel<FulfillmentCenterModel> {


    public DisplayCenterTable () {
        super();
    }

    public DisplayCenterTable (List<FulfillmentCenterModel> rows) {
        super(rows);
    }

    @Override
    protected Object getValueAt(FulfillmentCenterModel center, int columnIndex) {
        Object tmp;
        tmp = center.name;
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

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }
}