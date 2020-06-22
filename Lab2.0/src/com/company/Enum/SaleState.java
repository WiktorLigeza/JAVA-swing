package com.company.Enum;

public enum SaleState {
    zamowiony,
    w_magazynie,
    sprzedany;


    public String toString(){
        switch (this){
            case sprzedany: return"sprzedany";
            case zamowiony: return "zamowiony";
            case w_magazynie: return "w magazynie";
            default: return "";
        }
    }
}
