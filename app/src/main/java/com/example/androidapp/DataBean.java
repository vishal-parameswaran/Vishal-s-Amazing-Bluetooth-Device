package com.example.androidapp;

public class DataBean {
    private String mTemperature;
    private String mName;

    public DataBean(String temperature,String name){
        mTemperature = temperature;
        mName = name;
    }

    public String getTemperature(){
        return mTemperature;
    }
    public  String getName(){
        return mName;
    }
}
