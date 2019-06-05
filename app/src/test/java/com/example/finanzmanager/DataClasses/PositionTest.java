package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PositionTest {
    private Position position;
    private Date date = mock(Date.class);
    private double value = 10.10;
    // accepted delta for value
    private double delta = 0.01;
    private boolean recurring = true;
    private String category = "cat";
    private String description = "is a cat";

    @Before
    public void setUp() {
        position = new Position(date, value, recurring, category, description);
    }

    @After
    public void tearDown() {
        position = null;
    }

    @Test
    public void constructorTest(){
        position = new Position(date, value, recurring, category, description);
        assertNotNull(position);
    }

    // TODO: printPosition entfernen
    /*
    @Test
    public void printPosition() {
        String expected = ", Betrag: " + value + ", Kategorie: " + category + "\r\n";
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        position.printPosition();
        assertEquals(expected, outContent.toString());
    }
    */

    @Test
    public void getDate() {
        Date actual = position.getDate();
        assertEquals(date, actual);
    }

    @Test
    public void getValue() {
        double actual = position.getValue();
        assertEquals(value, actual, delta);

    }

    @Test
    public void getRecurring() {
        boolean actual = position.getRecurring();
        assertEquals(recurring, actual);
    }

    @Test
    public void getCategory() {
        String actual = position.getCategory();
        assertEquals(category, actual);
    }

    @Test
    public void getDescription(){
        String actual = position.getDescription();
        assertEquals(description, actual);
    }

    @Test
    public void getInfo(){
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

        String actual = position.getInfo();

        assertEquals(expected, actual);
    }

    @Test
    public void getInfoDate(){
        StringBuffer info = new StringBuffer();
        int value_size = 10;
        int description_size = 17;
        int date_size = 19;
        info.append(" ");
        info.append(String.format("%1$-" + value_size + "s", Double.toString(value)).replace(' ', '\t'));
        info.append(String.format("%1$-" + description_size + "s", description).replace(' ', '\t'));
        info.append(String.format("%1$" + date_size + "s", date.getString()).replace(' ', '\t'));
        String expected = info.toString();

        String actual = position.getInfoDate();

        assertEquals(expected, actual);
    }
}