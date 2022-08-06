package com.model;

import java.util.ArrayList;

public class TransactionModel {
    String url,date,from,to;
    ArrayList<FormModel> data;

    public TransactionModel(String url, String date, String from, String to) {
        this.url = url;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public ArrayList<FormModel> getData() {
        return data;
    }
}
