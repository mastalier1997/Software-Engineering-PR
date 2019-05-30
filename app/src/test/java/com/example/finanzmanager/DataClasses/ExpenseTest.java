package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ExpenseTest {
    private Expense expense;
    private Date date = mock(Date.class);
    private double value = 10.10;
    private boolean recurring = false;
    private String category = "cat";
    private String description = "this is a cat";

    @Before
    public void setUp() {
        expense = new Expense(date, value, recurring, category, description);
    }

    @After
    public void tearDown() {
        expense = null;
    }

    // TODO: printExpense entfernen
    /*
    @Test
    public void printExpense() {
        String expected = ", Datum: , Betrag: " + value + ", Kategorie: " + category + "\r\n";
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expense.printExpense();
        assertTrue(outContent.toString().endsWith(expected));
    }
    */

    @Test
    public void getInfo() {
        String actual = expense.getInfo();
        String expected = value + "\t\t\t\t\t" + description;
        assertEquals(expected, actual);
    }
}