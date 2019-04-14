package com.example.finanzmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Ausgaben extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausgaben);
        Spinner spinner = (Spinner) findViewById(R.id.dropDownEinAus2);

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
                        Intent intent = new Intent(Ausgaben.this, einnahmeAusgabe.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton imageButton = (ImageButton) findViewById(R.id.barButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Bar.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.atmButton);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Abhebung.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.cinemaButton);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Kino.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.gasButton);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Sprit.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.fastFoodButton);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, FastFood.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.groceryButton);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Lebensmittel.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton7 = (ImageButton) findViewById(R.id.gymButton);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Fitnessstudio.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton8 = (ImageButton) findViewById(R.id.hotelButton);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Hotel.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton9 = (ImageButton) findViewById(R.id.travelButton);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben.this, Reisen.class);
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
