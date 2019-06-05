package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

        StringBuffer info = new StringBuffer();
        int value_size = 10;
        int description_size = 17;
        int category_size = 15;
        info.append(" ");
        info.append(String.format("%1$-" + value_size + "s", Double.toString(value)).replace(' ', '\t'));
        info.append(String.format("%1$-" + description_size + "s", description).replace(' ', '\t'));
        info.append(String.format("%1$-" + category_size + "s", category).replace(' ', '\t'));
        String repeat = "";
        if (recurring) {
            repeat = "ja";
        } else {
            repeat = "nein";
        }
        info.append(String.format("%1$" + 4 + "s", repeat).replace(' ', '\t'));
        String expected = info.toString();

        assertEquals(expected, actual);
    }
}