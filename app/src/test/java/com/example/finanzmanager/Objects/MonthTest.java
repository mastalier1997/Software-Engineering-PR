package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonthTest {
    private Month month;
    private int intMonth = 10;
    private int year = 2010;
    private double sum_income = 1000;
    private double sum_expense = 1000;
    private double balance = 0;
    // Delta fuer Double-Werte
    private double delta = 0.01;

    @Before
    public void setUp() {
        month = new Month(year, intMonth);
    }

    @After
    public void tearDown() {
        month = null;
    }

    @Test
    public void getSumIncome() {
        double actual = month.getSumIncome();
        double expected = 0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getSumExpense() {
        double actual = month.getSumExpense();
        double expected = 0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getBalance() {
        double actual = month.getBalance();
        double expected = 0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getYear() {
        int actual = month.getYear();
        int expected = year;
        assertEquals(expected, actual);
    }

    @Test
    public void getMonth() {
        int actual = month.getMonth();
        int expected = intMonth;
        assertEquals(expected, actual);
    }

    @Test
    public void setIncome() {
        double expected = 1000;
        month.setIncome(expected);
        double actual = month.getSumIncome();
        assertEquals(expected, actual, delta);
    }

    @Test
    public void setExpense() {
        double expected = 1000;
        month.setExpense(expected);
        double actual = month.getSumExpense();
        assertEquals(expected, actual, delta);
    }

    @Test
    public void ChangeExpenseAndIncomeTest(){
        // Variablen fuer Test
        double one = 1000, two = -2000, three = 1050, four = 1000;

        month.setIncome(one);
        month.setIncome(two);
        month.setIncome(three);

        month.setExpense(one);
        month.setExpense(two);
        month.setExpense(four);

        // sum_income
        assertEquals((one+two+three), month.getSumIncome(), delta);
        // sum_expense
        assertEquals((one+two+four), month.getSumExpense(), delta);
        // balance
        assertEquals((three-four), month.getBalance(), delta);
    }
}