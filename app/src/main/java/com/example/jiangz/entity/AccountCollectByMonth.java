package com.example.jiangz.entity;

/**
 * Created by JiangZ on 2015-09-19.
 */
public class AccountCollectByMonth {
    private String currentMonth;

    private Double monthTotal;

    private Double todayTotal;

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Double getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(Double monthTotal) {
        this.monthTotal = monthTotal;
    }

    public Double getTodayTotal() {
        return todayTotal;
    }

    public void setTodayTotal(Double todayTotal) {
        this.todayTotal = todayTotal;
    }
}
