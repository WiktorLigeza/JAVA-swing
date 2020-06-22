package com.company;
import com.company.GUI.FulfillmentCenterGUI;

public class Main {

    public static void main(String[] args) {

      // FulfillmentCenterGUI GUI = new FulfillmentCenterGUI();
      // GUI.setVisible(true);

      java.awt.EventQueue.invokeLater(() -> new FulfillmentCenterGUI().setVisible(true));

    }
}
