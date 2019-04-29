package com.imwxz.store.entity;

public class StatEntity {
    private int totOrders;
    private int totBooks;
    private int totMoney;
    private int minTime;
    private int maxTime;

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
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
