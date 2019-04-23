package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ExpenseTest {
    private Expense expense;
    private Date date = mock(Date.class);
    private double value = 10.10;
    private boolean recurring = false;
    private String category = "cat";
    private String description = "this is a cat";
    // id of expense
    private int id = 0;

    @Before
    public void setUp() {
        expense = new Expense(date, value, recurring, category, description);
    }

    @After
    public void tearDown() {
        expense = null;
    }

    @Test
    public void printExpense() {
        String expected = "Id: " + id + ", Datum: , Betrag: " + value + ", Kategorie: " + category + "\r\n";
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expense.printExpense();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void getInfo() {
        String actual = expense.getInfo();
        String expected = value + "\t\t\t\t\t" + description;
        assertEquals(expected, actual);
    }
}