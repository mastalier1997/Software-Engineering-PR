package com.example.finanzmanager.activity_classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class expense_overviewTest {

    expense_overview activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(expense_overview.class)
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