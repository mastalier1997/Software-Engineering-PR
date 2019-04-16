package com.example.finanzmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static PositionList account = new PositionList();
    static int incomeSum = 0;
    int value = 0;
    String description = "";
    String category = "";
    int day, month, year = 0;
    Boolean repeat = false;

    static int expenseSum = 0;
    TextView sumIncome;
    TextView sumExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sumIncome = (TextView) findViewById(R.id.textView_sumIncome);
        sumExpense = (TextView) findViewById(R.id.textView_sumExpense);

        String Income = Integer.toString(incomeSum);
        String Expense = Integer.toString(expenseSum);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("type").equals("income")) {
                incomeSum = incomeSum + bundle.getInt("value");
                description = bundle.getString("description");
                category = bundle.getString("category");
                repeat = bundle.getBoolean("repeats");
                day = bundle.getInt("day");
                month = bundle.getInt("month");
                year = bundle.getInt("year");
                Date date = new Date(day, month, year);
                account.addIncome(date, value, repeat, category, description);
                Income = Integer.toString(incomeSum);
            } else if (bundle.getString("type").equals("expense")){
                expenseSum = expenseSum + bundle.getInt("value");
                description = bundle.getString("description");
                category = bundle.getString("category");
                repeat = bundle.getBoolean("repeats");
                day = bundle.getInt("day");
                month = bundle.getInt("month");
                year = bundle.getInt("year");
                Date date = new Date(day, month, year);
                account.addExpense(date, value, repeat, category, description);
                Expense = Integer.toString(expenseSum);
            }
        }
        sumIncome.setText(Income);
        sumExpense.setText(Expense);

        //Plus Button Weiterleitung
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Einnahmen_menue.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //DropDown für Monate
        Spinner dropdown = findViewById(R.id.dropDown);
        String[] items = new String[]{"Jänner", "Februar", "März"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_income) {
            // Handle the camera action
        } else if (id == R.id.nav_expense) {

        } else if (id == R.id.nav_graph) {

        } else if (id == R.id.nav_filter) {

        } else if (id == R.id.nav_import) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
