package com.example.finanzmanager.DataClasses;

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


    /**
     * @return sum_income
     */
    public double getSumIncome() {return sum_income;}

    /**
     * @return sum_expense
     */
    public double getSumExpense() {return sum_expense;}

    /**
     * @return balance
     */
    public double getBalance() {return balance;}

    /**
     * @return year
     */
    public int getYear() {return year;}

    /**
     * @return month
     */
    public int getMonth() {return month;}

    /**
     * updates the sum of all incomes from a month and also its balance
     * @param value value from the new Income
     */
    void setIncome(double value) {
        sum_income = sum_income + value;
        balance = sum_income - sum_expense;
    }


    /**
     * updates the sum of all expenses from a month and also its balance
     * @param value
     */
    void setExpense(double value) {
        sum_expense = sum_expense + value;
        balance = sum_income - sum_expense;
    }

}
