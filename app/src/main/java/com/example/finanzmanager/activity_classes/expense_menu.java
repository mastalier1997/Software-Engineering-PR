package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.R;

import java.util.ArrayList;
import java.util.List;

public class expense_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Spinner spinner = (Spinner) findViewById(R.id.dropDown_In_Out);

        List<String> categories= new ArrayList<>();
        categories.add(0,"Ausgaben");
        categories.add("Einahmen");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getItemAtPosition(position).equals("Ausgaben")){
                    //do nothing
                }else {
                    String item= adapterView.getItemAtPosition(position).toString();

                    if (adapterView.getItemAtPosition(position).equals("Einahmen")){
                        Intent intent = new Intent(expense_menu.this, income_menu.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button createCategorie = (Button) findViewById(R.id.Button_createCat_expense);
        createCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(expense_menu.this, create_category_expense.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Es wird ein integer mitgegeben, mit dem das richtige Bild und Text ausgewählt wird
        ImageButton imageButton = (ImageButton) findViewById(R.id.Button_cash);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                // der name ist der Schlüssel, mit dem dieser Wert in der Zielklasse wieder gefunden wird
                intent.putExtra("kategorie", 1);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.Button_atm);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 2);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.Button_cinema);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 3);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.Button_gas);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 4);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.Button_fastFood);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 5);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.Button_grocery);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 6);
                startActivity(intent);
            }
        });

        ImageButton imageButton7 = (ImageButton) findViewById(R.id.Button_gym);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 7);
                startActivity(intent);
            }
        });

        ImageButton imageButton8 = (ImageButton) findViewById(R.id.Button_hotel);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 8);
                startActivity(intent);
            }
        });

        ImageButton imageButton9 = (ImageButton) findViewById(R.id.Button_travel);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 9);
                startActivity(intent);
            }
        });

        Intent extraIntent = getIntent();
        final String extraName = extraIntent.getStringExtra("Test");
        TextView imageText = findViewById(R.id.textView_extraCat_expense);
        imageText.setText(extraName);
        ImageButton imageButton10 = (ImageButton) findViewById(R.id.Button_extraCatExp);
        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 10);
                intent.putExtra("Test", extraName);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
