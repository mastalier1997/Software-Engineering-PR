package com.example.finanzmanager.Objects;

public class Month {

    private int year;
    private int month;
    private int sum_income;
    private int sum_expense;
    private int balance;

    Month(int year, int month) {
        this.year = year;
        this.month = month;
        sum_income = 0;
        sum_expense = 0;
        balance = 0;
    }

    public int getSumIncome() {return sum_income;}
    public int getSumExpense() {return sum_expense;}
    public int getBalance() {return balance;}
    public int getYear() {return year;}
    public int getMonth() {return month;}

    void setIncome(int value) {
        sum_income = sum_income + value;
        balance = sum_income - sum_expense;
    }

    void setExpense(int value) {
        sum_expense = sum_expense + value;
        balance = sum_income - sum_expense;
    }

}
