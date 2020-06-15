package com.example.androidapp;

public class lastTemperaturesList  {
    private String mDateItem;
    private int mTemperature;
    public lastTemperaturesList(String dateItem, int temperature){
        mDateItem = dateItem;
        mTemperature = temperature;
    }
    public String getDateItem(){
        return mDateItem;
    }
    public int getTemperature(){
        return mTemperature;
    }
}
