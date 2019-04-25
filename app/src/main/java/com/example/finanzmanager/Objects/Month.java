package com.example.finanzmanager.Objects;

public class Month {

    private int year;
    private int month;
    private double sum_income;
    private double sum_expense;
    private double balance;

    Month(int year, int month) {
        this.year = year;
        this.month = month;
        sum_income = 0;
        sum_expense = 0;
        balance = 0;
    }

    public double getSumIncome() {return sum_income;}
    public double getSumExpense() {return sum_expense;}
    public double getBalance() {return balance;}
    public int getYear() {return year;}
    public int getMonth() {return month;}

    void setIncome(double value) {
        sum_income = sum_income + value;
        balance = sum_income - sum_expense;
    }

    void setExpense(double value) {
        sum_expense = sum_expense + value;
        balance = sum_income - sum_expense;
    }

}
