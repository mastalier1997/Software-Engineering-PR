package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class IncomeTest {
    private Income income;
    private Date date = mock(Date.class);
    private double value = 10.10;
    private boolean recurring = false;
    private String category = "cat";
    private String description = "this is a cat";

    @Before
    public void setUp() {
        income = new Income(date, value, recurring, category, description);
    }

    @After
    public void tearDown() {
        income = null;
    }

    // TODO: printIncome() entfernen
    /*
    @Test
    public void printIncome() {
        String expected = ", Datum: , Betrag: " + value + ", Kategorie: " + category + "\r\n";
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        income.printIncome();
        assertTrue(outContent.toString().endsWith(expected));
    }
    */

    @Test
    public void getInfo() {
        String actual = income.getInfo();
        String expected = value + "\t\t\t\t\t" + description;
        assertEquals(expected, actual);
    }
}