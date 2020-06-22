package com.company.GUI;

import com.company.Enum.SaleState;
import com.company.GenerateData;
import com.company.Models.FulfillmentCenterContainer;
import com.company.Models.FulfillmentCenterModel;
import com.company.Models.ItemModel;
import com.sun.jdi.Value;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FulfillmentCenterGUI extends JFrame{
    private JPanel rootPanel;
    private JTable centerInfoTable;
    private JTable productInfoTable;
    private JButton removeCenterButton;
    private JButton addCenterButton;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JTable productTable;
    private JTable centerTable;
    private JComboBox stateComboBox;
    private JTextField filterTextField;
    private JButton sortCentersByLoadButton;
    private JLabel centerInfoLabel;
    private JLabel productInfoLabel;

    //models
    FulfillmentCenterContainer fcc;
    FulfillmentCenterModel selectedCenter;
    ItemModel selectedProduct;

    //display models
    private DisplayCenterTable centerModel;
    private DisplayCenterInfoTable centerInfoModel;
    private DisplayProductTable productModel;
    private DisplayProductInfoTable productInfoModel;

    //lists
    List<FulfillmentCenterModel> selectedCentersList;
    List<ItemModel> selectedCenterProductsList;
    List<ItemModel> selectedProductsList; //product info
    //private List<ItemModel> itemsList;
    // private List<FulfillmentCenterModel> centersList;
    List<ItemModel> temp = new ArrayList<>();








    ////////////////////////////////////////CONSTRUCTOR\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public FulfillmentCenterGUI(){

        //preset
        add(rootPanel);
        setTitle("nazwa");
        setSize(900,500);
        setDefaultCloseOperation(3);

        //////////////////////////Buttons\\\\\\\\\\\\\\\\\\\\\\\
        //Center buttons
        addCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = JOptionPane.showInputDialog("Enter Name of fulfillment center");
                String maximumCapacity =JOptionPane.showInputDialog("Enter Max capacity of center");
                String city = JOptionPane.showInputDialog("Enter city");

                if(name.length()>0 && city.length()>0 && maximumCapacity.length()>0){
                    fcc.addCenter(name, Double.parseDouble(maximumCapacity),city);
                    centerModel.fireTableDataChanged();
                }
                else  JOptionPane.showMessageDialog(null,"invalid data");

            }
        });
        sortCentersByLoadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fcc.sortByFillLvl();
                centerModel.fireTableDataChanged();
            }
        });
        removeCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int i = centerTable.getSelectedRow();
                    selectedCenter = fcc.CentersList.get(i);
                    fcc.removeCenter(selectedCenter);
                    centerModel.fireTableDataChanged();
                    centerInfoModel.fireTableDataChanged();
                    productInfoModel.fireTableDataChanged();
                    selectedCenterProductsList.removeAll(temp);
                    productModel.fireTableDataChanged();
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null,"you have to select something");
                }
            }
        });

        //Product buttons
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = JOptionPane.showInputDialog("Enter Name of fulfillment center");
                String state = JOptionPane.showInputDialog("Enter items sale state");
                double weight = Double.parseDouble(JOptionPane.showInputDialog("Enter weight"));
                SaleState saleState;

                if(state.equals("zamowiony")){ saleState = SaleState.zamowiony;}
                else if(state.equals("w magazynie")){saleState = SaleState.w_magazynie;}
                else saleState = SaleState.sprzedany;
                if(!selectedCentersList.get(1).addProduct(new ItemModel(name,saleState,weight))){
                    JOptionPane.showMessageDialog(null,"maximum weight has been exceeded");
                }
                else{
                    //refresh
                    selectedCenterProductsList.add(selectedCentersList.get(1).itemsList.get(selectedCentersList.get(1).itemsList.size()-1));
                    temp.add(selectedCentersList.get(1).itemsList.get(selectedCentersList.get(1).itemsList.size()-1));
                    JOptionPane.showMessageDialog(null,"added successfully"); }
                centerInfoModel.fireTableDataChanged();
                productModel.fireTableDataChanged();
            }
        });
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    int i = productTable.getSelectedRow();
                    selectedProduct = selectedCenterProductsList.get(i);
                    selectedCentersList.get(1).removeProduct(selectedProduct);
                    selectedCenterProductsList.remove(selectedProduct);
                    productModel.fireTableDataChanged();
                    productInfoModel.fireTableDataChanged();
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null,"you have to select something");
                }
            }
        });




        //show selected
        ListSelectionModel modelc = centerTable.getSelectionModel();
        modelc.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = modelc.getMinSelectionIndex();
                //clear table before displaying
                if(selectedCentersList.size() != 1) {
                    selectedCentersList.remove( selectedCentersList.get(1));
                }
                //display selected
                if(!modelc.isSelectionEmpty() && selectedCentersList.size() == 1 ){
                    //populate centerInfoTable
                    selectedCentersList.add(fcc.CentersList.get(i));
                    centerInfoModel.fireTableDataChanged();
                }
            }
        });


        ListSelectionModel modelp = productTable.getSelectionModel();
        modelp.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //clear table before displaying
                if(selectedProductsList.size() != 1) {
                    selectedProductsList.remove( selectedProductsList.get(1));
                }
                //display selected
                if(!modelp.isSelectionEmpty() && selectedProductsList.size() == 1){
                    int i = modelp.getMinSelectionIndex();
                    selectedProductsList.add(selectedCenterProductsList.get(i));
                    productInfoModel.fireTableDataChanged();
                }
            }
        });

        centerTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if(selectedCenterProductsList.size() == 1){
                    selectedCenterProductsList.remove(0);
                }
                else if(selectedCenterProductsList.size()>0){
                    selectedCenterProductsList.removeAll(temp);
                }
                if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
                     selectedCenterProductsList.addAll(fcc.CentersList.get(row).itemsList);
                     productModel.fireTableDataChanged();
                     temp = new ArrayList<>(fcc.CentersList.get(row).itemsList);
                }
            }
        });

        //extras
        filterTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                   String clue = filterTextField.getText();
                   if(selectedCentersList.get(1).searchPartial(clue).size()>0){
                       selectedCenterProductsList.removeAll(temp);
                       selectedCenterProductsList.addAll(selectedCentersList.get(1).searchPartial(clue));
                       productModel.fireTableDataChanged();
                   }
                   else  JOptionPane.showMessageDialog(null,"no such a product");
                }
            }
        });
    }





    ////////////////////////////////////////CREATOR\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private void createUIComponents() {
         //Center Table
         centerTable = new JTable(new DisplayCenterTable(GenerateData.getProductData().CentersList));
         fcc = GenerateData.getProductData();
         centerModel = new DisplayCenterTable(fcc.CentersList);
         centerTable = new JTable(centerModel);

         //Center Info Table
         centerInfoTable = new JTable(new DisplayCenterInfoTable(GenerateData.getBlankCenter()));
         selectedCentersList = GenerateData.getBlankCenter();
         centerInfoModel = new DisplayCenterInfoTable(selectedCentersList);
         centerInfoTable = new JTable(centerInfoModel);


         //Product Table
         productTable = new JTable(new DisplayProductTable(GenerateData.getBlankProduct()));
         selectedCenterProductsList = GenerateData.getBlankProduct();
         productModel = new DisplayProductTable(selectedCenterProductsList);
         productTable = new JTable(productModel);

         //Product Info Table
         productInfoTable = new JTable(new DisplayProductTable(GenerateData.getBlankProduct()));
         selectedProductsList = GenerateData.getBlankProduct();
         productInfoModel = new DisplayProductInfoTable(selectedProductsList);
         productInfoTable = new JTable(productInfoModel);

    }
}
