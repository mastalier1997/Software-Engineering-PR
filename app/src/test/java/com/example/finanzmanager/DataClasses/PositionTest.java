package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PositionTest {
    private Random random;
    private Position position;
    private Date date;
    private double value;
    private boolean recurring;
    private String category;
    private String description;

    // accepted delta for value
    private double delta = 0.001;

    @Before
    public void setUp() {
        random = new Random();
        date = mock(Date.class);
        value = (random.nextInt(999999)+0.99);
        recurring = random.nextBoolean();
        category = "cat";
        description = "this is a cat";
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
        assertNotNull(position.getInfo());
        Position position1 = new Position(date, 1, !recurring,
                "1234asdfqwe", "qwertzuiopüasdfghjkl");
        assertNotNull(position1.getInfo());
    }

    @Test
    public void getInfoDate(){
        assertNotNull(position.getInfoDate());
        Position position1 = new Position(date, 1, !recurring,
                "1234asdfqwe", "qwertzuiopüasdfghjkl");
        assertNotNull(position1.getInfoDate());
    }
}