package com.company.Models;
import com.company.Enum.SaleState;
import java.util.Comparator;

public class ItemModel implements Comparable<ItemModel> {
    public String name;
    public SaleState state;
    public double weight;
    //public int quantity;


    public void summary(){
        System.out.println("name: " + this.name);
        System.out.println("condition: " + this.state);
        System.out.println("weight: " + this.weight);
       // System.out.println("quantity: " + this.quantity);
        System.out.println("--------------------------");
    }

    //constr
    public ItemModel( String name, SaleState state, double weight, int quantity) {
        this.name = name;
        this.state = state;
        this.weight = weight;
        //this.quantity = quantity;

    }

    @Override
    public int compareTo(ItemModel item) {
        //does
        if (this.name.equals(item.name)) {
            return 0;
        }
        //does not
        else {
            return -1;
        }
    }


    public String getName() {
        return name;
    }

    public SaleState getState() {
        return state;
    }

    public double getWeight() {
        return weight;
    }

    public ItemModel(String name,SaleState state, double weight){
        this.state = state;
        this.weight = weight;
        this.name = name;
    }

}
