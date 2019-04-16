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

public class Ausgaben_menue extends AppCompatActivity {

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
                        Intent intent = new Intent(Ausgaben_menue.this, Einnahmen_menue.class);
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

        // Es wird ein integer mitgegeben, mit dem das richtige Bild und Text ausgewählt wird
        ImageButton imageButton = (ImageButton) findViewById(R.id.barButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                // der name ist der Schlüssel, mit dem dieser Wert in der Zielklasse wieder gefunden wird
                intent.putExtra("kategorie", 1);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.atmButton);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 2);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.cinemaButton);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 3);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.gasButton);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 4);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.fastFoodButton);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 5);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.groceryButton);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 6);
                startActivity(intent);
            }
        });

        ImageButton imageButton7 = (ImageButton) findViewById(R.id.gymButton);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 7);
                startActivity(intent);
            }
        });

        ImageButton imageButton8 = (ImageButton) findViewById(R.id.hotelButton);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 8);
                startActivity(intent);
            }
        });

        ImageButton imageButton9 = (ImageButton) findViewById(R.id.travelButton);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Ausgaben_menue.this, neu_ausgabe.class);
                intent.putExtra("kategorie", 9);
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
