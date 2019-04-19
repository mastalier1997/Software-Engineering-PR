package com.example.finanzmanager.kategorie_alt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finanzmanager.MainActivity;
import com.example.finanzmanager.R;

import java.util.Calendar;

public class Gluecksspiel extends AppCompatActivity {

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gluecksspiel);

        final TextView textView=(TextView) findViewById(R.id.dateView2);
        ImageButton imageButton=(ImageButton) findViewById(R.id.dateButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar= Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Gluecksspiel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        textView.setText(mDay +"."+(mMonth+1)+"."+mYear);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton checkButton = (ImageButton) findViewById(R.id.checkButton5);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Gluecksspiel.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.cancelButton5);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Gluecksspiel.this, einnahmeAusgabe.class);
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
