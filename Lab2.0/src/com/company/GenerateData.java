package com.company;

import com.company.Enum.SaleState;
import com.company.Models.FulfillmentCenterContainer;
import com.company.Models.FulfillmentCenterModel;
import com.company.Models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class GenerateData {

    //populate fcc
    public static List<FulfillmentCenterModel> getBlankCenter(){
        List<FulfillmentCenterModel> blank = new ArrayList<>();
        blank.add(new FulfillmentCenterModel("", 0,""));
        return blank;
    }

    public static FulfillmentCenterContainer getProductData() {
        FulfillmentCenterContainer fcc = new FulfillmentCenterContainer();
        FulfillmentCenterModel fc;
        ItemModel i1 = new ItemModel("Wiktor", SaleState.sprzedany,21);
        ItemModel i2 = new ItemModel("Wiktoria", SaleState.w_magazynie,37);
        ItemModel i3 = new ItemModel("Witold", SaleState.zamowiony,55);
        ItemModel i4 = new ItemModel("i4", SaleState.sprzedany,212);
        ItemModel i5 = new ItemModel("i5", SaleState.sprzedany,212);
        ItemModel i6 = new ItemModel("Karol", SaleState.sprzedany,212);

        fcc.addCenter("Ikea",2137,"Brzesko");
        fcc.addCenter("Castorama",60,"Krk");
        fcc.addCenter("Wiedronca",1411,"Wadowice");

        //populate ItemsList
        fc = fcc.CentersList.get(2);
        fc.addProduct(i6);
        fc.addProduct(i5);

        fc = fcc.CentersList.get(1);
        fc.addProduct(i1);
        fc.addProduct(i2);

        fc = fcc.CentersList.get(0);
        fc.addProduct(i1);
        fc.addProduct(i4);
        fc.addProduct(i3);
        fc.addProduct(i4);

        return fcc;
    }



    public static List<ItemModel> getBlankProduct(){
        List<ItemModel> blank = new ArrayList<>();
        blank.add(new ItemModel("select center", null,0));
        return blank;
    }


}
