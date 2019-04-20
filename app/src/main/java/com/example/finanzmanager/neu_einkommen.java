package com.example.finanzmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class neu_einkommen extends AppCompatActivity {

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    ImageView imgView;
    TextView txtView;
    CheckBox repeat;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neu_einkommen);

        textView=(TextView) findViewById(R.id.date_income);
        ImageButton imageButton=(ImageButton) findViewById(R.id.dateButton_income);

        Intent intent = getIntent();
        int category = intent.getIntExtra("kategorie", 0);

        repeat = (CheckBox) findViewById(R.id.checkBox_income);
        calendar= Calendar.getInstance();

        //Anzeige des richtigen Bildes und Textes
        switch(category) {
            case 1:
                imgView = (ImageView) findViewById(R.id.image_praemie);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_praemie);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgView = (ImageView) findViewById(R.id.image_investition);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_investition);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgView = (ImageView) findViewById(R.id.image_gehalt);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_gehalt);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgView = (ImageView) findViewById(R.id.image_dividenden);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_dividenden);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgView = (ImageView) findViewById(R.id.image_gluecksspiel);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_gluecksspiel);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 6:
                imgView = (ImageView) findViewById(R.id.image_rueckerstattung);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_rueckerstattung);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 7:
                imgView = (ImageView) findViewById(R.id.image_rueckerstattung);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_rueckerstattung);
                txtView.setVisibility(View.VISIBLE);
                break;
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(neu_einkommen.this, new DatePickerDialog.OnDateSetListener() {
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

        ImageButton checkButton = (ImageButton) findViewById(R.id.checkButton_income);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Betrag abspeichern
                EditText add = (EditText) findViewById(R.id.editText_income);
                int addNumber = Integer.parseInt(add.getText().toString());

                //Datum abspeichern
                Integer day = 0;
                Integer month = 0;
                Integer year = 0;
                if (calendar != null) {
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    month = calendar.get(Calendar.MONTH);
                    year = calendar.get(Calendar.YEAR);
                }

                //kategorie speichern
                String category = txtView.getText().toString();

                //Beschreibung speichern
                EditText descriptionText = (EditText) findViewById(R.id.editText_beschreibung_einnahme);
                String description = descriptionText.getText().toString();

                //wiederkehrend
                Boolean repeats = false;
                if(repeat.isChecked()) repeats = true;

                //alles in einem Bundle gespeichert
                Bundle extras = new Bundle();
                extras.putInt("value", addNumber);
                extras.putInt("day", day);
                extras.putInt("month", month);
                extras.putInt("year", year);
                extras.putString("category", category);
                extras.putString("description", description);
                extras.putBoolean("repeats", repeats);
                extras.putString("type", "income");

                Intent intent= new Intent(neu_einkommen.this, MainActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.cancelButton_income);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(neu_einkommen.this, Einnahmen_menue.class);
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