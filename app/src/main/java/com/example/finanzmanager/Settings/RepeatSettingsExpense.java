package com.example.finanzmanager.Settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.DataClasses.Expense;
import com.example.finanzmanager.R;
import com.example.finanzmanager.ActivityClasses.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RepeatSettingsExpense extends AppCompatActivity {

    private ListView listView_expense_repeat;
    private ArrayAdapter<String> stringArrayAdapter_expense_repeat;
    private ArrayList<String> stringArrayList_expense_repeat = new ArrayList<>();
    private ArrayList<Expense> repeating_expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.repeat_settings_expense);

        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='"+getResources().getColor(R.color.colorAccent)+"'>Übersicht Ausgaben </font>"));

        //ListView
        listView_expense_repeat = (ListView) findViewById(R.id.listView_repeating_expense);
        //Befüllung aus account
        repeating_expenses = MainActivity.account.get_repeatingExpenseList();

        for(Expense i : repeating_expenses) {
            stringArrayList_expense_repeat.add(i.getInfo());
        }

        //Ausgabe in ListView
        stringArrayAdapter_expense_repeat = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, R.id.textView_list_white, stringArrayList_expense_repeat);
        listView_expense_repeat.setAdapter(stringArrayAdapter_expense_repeat);

        Spinner dropdown = findViewById(R.id.dropDown_OutIn_repeat);
        List<String> items2= new ArrayList<>();
        items2.add(0,"Ausgaben");
        items2.add("Einnahmen");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        adapter.setDropDownViewResource(R.layout.spinner);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                if (adapterView.getItemAtPosition(position).equals("Ausgaben")){
                    //do nothing
                }else {
                    String item= adapterView.getItemAtPosition(position).toString();

                    if (adapterView.getItemAtPosition(position).equals("Einnahmen")){
                        Intent intent = new Intent(RepeatSettingsExpense.this, RepeatSettingsIncome.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listView_expense_repeat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Expense info = repeating_expenses.get(i);
                Intent intent = new Intent(RepeatSettingsExpense.this, InfoRepeatingExpense.class);
                intent.putExtra("description", info.getDescription());
                intent.putExtra("value", info.getValue());
                startActivity(intent);
            }
        });

    }

    /**
     * back button to Main Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }

        return super.onOptionsItemSelected(item);
    }


}
