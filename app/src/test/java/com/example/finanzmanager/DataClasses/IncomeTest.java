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
public class IncomeTest {
    private Income income;
    private Random random;
    private Date date;
    private double value;
    private boolean recurring;
    private String category;
    private String description;

    @Before
    public void setUp() {
        random = new Random();
        date = mock(Date.class);
        value = (random.nextInt(999999)+0.99);
        recurring = random.nextBoolean();
        category = "cat";
        description = "this is a cat";
        income = new Income(date, value, recurring, category, description);
    }

    @After
    public void tearDown() {
        income = null;
    }

    @Test
    public void checkNotNull(){
        assertNotNull(income);
    }

    @Test
    public void getId(){
        assertEquals(0, income.getId());
        Income i2 = new Income(date, value, recurring, category, description);
        assertEquals(1, i2.getId());
    }
}