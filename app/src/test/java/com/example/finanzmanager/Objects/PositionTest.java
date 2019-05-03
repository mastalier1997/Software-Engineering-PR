package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PositionTest {
    private Position position;
    private Date date = mock(Date.class);
    private double value = 10.10;
    // accepted delta for value
    private double delta = 0.01;
    private boolean recurring = false;
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
}