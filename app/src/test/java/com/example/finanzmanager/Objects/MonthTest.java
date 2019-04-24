package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonthTest {
    private Month month;
    private int intMonth = 10;
    private int year = 2010;
    private int sum_income = 1000;
    private int sum_expense = 1000;
    private int balance = 0;

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
        int actual = month.getSumIncome();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void getSumExpense() {
        int actual = month.getSumExpense();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void getBalance() {
        int actual = month.getBalance();
        int expected = 0;
        assertEquals(expected, actual);
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
        int expected = 1000;
        month.setIncome(expected);
        int actual = month.getSumIncome();
        assertEquals(expected, actual);
    }

    @Test
    public void setExpense() {
        int expected = 1000;
        month.setExpense(expected);
        int actual = month.getSumExpense();
        assertEquals(expected, actual);
    }

    @Test
    public void ChangeExpenseAndIncomeTest(){
        // Variablen fuer Test
        int one = 1000, two = -2000, three = 1050, four = 1000;

        month.setIncome(one);
        month.setIncome(two);
        month.setIncome(three);

        month.setExpense(one);
        month.setExpense(two);
        month.setExpense(four);

        // sum_income
        assertEquals((one+two+three), month.getSumIncome());
        // sum_expense
        assertEquals((one+two+four), month.getSumExpense());
        // balance
        assertEquals((three-four), month.getBalance());
    }
}