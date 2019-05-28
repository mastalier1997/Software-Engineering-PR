package com.example.finanzmanager.activity_classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import addNew.new_expense;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class new_expenseTest {

    new_expense activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(new_expense.class)
                .create()
                .resume()
                .get();
    }

    @After
    public void tearDown(){
        activity = null;
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }

}