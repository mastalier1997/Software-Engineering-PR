package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionListTest {
    private PositionList pl;
    private int year = 2010;
    private int month = 10;
    private double d = 10.10;
    private boolean recurring = false;
    private String category = "cat";
    private String description = "this is a cat";
    private Date date = new Date(10, month, year);

    @Before
    public void setUp() {
        pl = new PositionList();

    }

    @After
    public void tearDown() {
        pl = null;
    }

    @Test
    public void getIncomeFromDate() {
        assertEquals(0, pl.getIncomeFromDate(month, year).size());

        pl.addIncome(date, d, recurring, category, description);
        assertEquals(1, pl.getIncomeFromDate(month, year).size());
        // Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        assertEquals(0, pl.getIncomeFromDate(month2, year).size());
    }

    @Test
    public void getExpenseFromDate() {
        assertEquals(0, pl.getExpenseFromDate(month, year).size());

        pl.addExpense(date, d, recurring, category, description);
        assertEquals(1, pl.getExpenseFromDate(month, year).size());
        // Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        assertEquals(0, pl.getExpenseFromDate(month2, year).size());
    }

    @Test
    public void addIncome() {
        pl.addIncome(date, d, recurring, category, description);
        pl.addIncome(date, d+1, recurring, category, description);
        pl.addIncome(date, d+2, recurring, category, description);
        assertEquals(3, pl.getIncomeFromDate(month, year).size());
    }

    @Test
    public void addExpense() {
        pl.addExpense(date, d, recurring, category, description);
        pl.addExpense(date, d+1, recurring, category, description);
        pl.addExpense(date, d+2, recurring, category, description);
        assertEquals(3, pl.getExpenseFromDate(month, year).size());
    }

    @Test
    public void getIncome() {
        pl.addIncome(date, d, recurring, category, description);
        pl.addIncome(date, d+1, recurring, category, description);
        pl.addIncome(date, d+2, recurring, category, description);
        assertNotNull(pl.getIncome(2));
       // assertNull(pl.getIncome(4));
    }

    @Test
    public void getExpense() {
        pl.addExpense(date, d, recurring, category, description);
        pl.addExpense(date, d+1, recurring, category, description);
        pl.addExpense(date, d+2, recurring, category, description);
        assertNotNull(pl.getExpense(2));
        // assertNull(pl.getExpense(4));
    }

    /* Unbenutzte Methoden
    @Test
    public void incomeLength() {
    }

    @Test
    public void incomeLengthInMonth() {
    }

    @Test
    public void expenseLength() {
    }

    @Test
    public void expenseLengthInMonth() {
    }

    @Test
    public void printIncome() {
    }

    @Test
    public void printExpense() {
    }

    @Test
    public void printIncomeCondition() {
    }

    @Test
    public void printExpenseCondition() {
    } */
}