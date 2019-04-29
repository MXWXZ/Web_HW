package com.imwxz.store.entity;

import java.sql.Timestamp;

public class StatEntity {
    private int totOrders;
    private int totBooks;
    private int totMoney;
    private Timestamp minTime;
    private Timestamp maxTime;

    public Timestamp getMinTime() {
        return minTime;
    }

    public void setMinTime(Timestamp minTime) {
        this.minTime = minTime;
    }

    public Timestamp getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Timestamp maxTime) {
        this.maxTime = maxTime;
    }

    public int getTotOrders() {
        return totOrders;
    }

    public void setTotOrders(int totOrders) {
        this.totOrders = totOrders;
    }

    public int getTotBooks() {
        return totBooks;
    }

    public void setTotBooks(int totBooks) {
        this.totBooks = totBooks;
    }

    public int getTotMoney() {
        return totMoney;
    }

    public void setTotMoney(int totMoney) {
        this.totMoney = totMoney;
    }
}
