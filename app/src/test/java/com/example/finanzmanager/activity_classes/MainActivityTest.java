package com.example.finanzmanager.activity_classes;

import android.view.Menu;
import android.view.MenuItem;
import com.example.finanzmanager.Objects.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
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

    @Test
    public void saveToAccount() {
        MainActivity.saveToAccount();
        //TODO: Test überlegen
    }

    @Test
    public void addIncomeFromCurrentMonth() {
        Date d = new Date(10, 10, 2010);
        double val = 10.10;
        String cat = "cat";
        String desc = "this is a cat";
        activity.addIncomeFromCurrentMonth(d, val, cat, desc);
        // TODO: Besseres assert überlegen
        assertEquals(12, MainActivity.months.fromMonth(2010, 10).size());

    }

    @Test
    public void addExpenseFromCurrentMonth() {
        Date d = new Date(10, 10, 2010);
        double val = 10.10;
        String cat = "cat";
        String desc = "this is a cat";
        MainActivity.months.size();
        activity.addExpenseFromCurrentMonth(d, val, cat, desc);
        // TODO: Besseres assert überlegen
        assertEquals(12, MainActivity.months.size(), 0.01);
    }

    @Test
    public void onBackPressed() {
        activity.onBackPressed();
        // TODO: Test überlegen
    }

    @Test
    public void onCreateOptionsMenu() {
        boolean t = true;
        Menu m = mock(Menu.class);
        // TODO: Test ändern
        assertTrue(t);
    }

    @Test
    public void onOptionsItemSelected() {
        boolean t = true;
        MenuItem m = mock(MenuItem.class);
        // TODO: Test ändern
        assertTrue(t);
    }

    @Test
    public void onNavigationItemSelected() {
        MenuItem m = mock(MenuItem.class);
        //activity.onNavigationItemSelected(m);
        // TODO: Test schreiben
    }

    @Test
    public void deleteData() {
        activity.deleteData();
        assertEquals(0, activity.value);
        // TODO: Umschreiben
    }
}