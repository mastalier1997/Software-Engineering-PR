package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.finanzmanager.Objects.Expense;
import com.example.finanzmanager.Objects.Income;
import com.example.finanzmanager.R;

import java.util.ArrayList;
import java.util.List;

public class repeat_settings_expense extends AppCompatActivity {

    private ListView listView_expense_repeat;
    private ArrayAdapter<String> stringArrayAdapter_expense_repeat;
    private ArrayList<String> stringArrayList_expense_repeat = new ArrayList<>();
    private ArrayList<Expense> repeating_expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeat_settings_expense);

        //ListView
        listView_expense_repeat = (ListView) findViewById(R.id.listView_repeating_expense);
        //Befüllung aus account
        repeating_expenses = MainActivity.account.get_repeatingExpenseList();

        for(Expense i : repeating_expenses) {
            stringArrayList_expense_repeat.add(i.getInfo());
        }

        //Ausgabe in ListView
        stringArrayAdapter_expense_repeat = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, R.id.textView_list_black, stringArrayList_expense_repeat);
        listView_expense_repeat.setAdapter(stringArrayAdapter_expense_repeat);

        Spinner dropdown = findViewById(R.id.dropDown_OutIn_repeat);
        List<String> items2= new ArrayList<>();
        items2.add(0,"Ausgaben");
        items2.add("Einnahmen");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getItemAtPosition(position).equals("Ausgaben")){
                    //do nothing
                }else {
                    String item= adapterView.getItemAtPosition(position).toString();

                    if (adapterView.getItemAtPosition(position).equals("Einnahmen")){
                        Intent intent = new Intent(repeat_settings_expense.this, repeat_settings_income.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.button_cancel_expense_setting);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(repeat_settings_expense.this, settings.class);
                startActivity(intent);
            }
        });

        listView_expense_repeat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Expense info = repeating_expenses.get(i);
                Intent intent = new Intent(repeat_settings_expense.this, info_repeating_expense.class);
                intent.putExtra("description", info.getDescription());
                intent.putExtra("value", info.getValue());
                startActivity(intent);
            }
        });

    }

}
