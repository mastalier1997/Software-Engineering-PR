package com.example.finanzmanager.activity_classes;

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

import com.example.finanzmanager.R;

import java.util.Calendar;

public class new_income extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    ImageView imgView;
    TextView txtView;
    CheckBox repeat;
    TextView textView;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);

        textView=(TextView) findViewById(R.id.textView_date_income);
        ImageButton imageButton=(ImageButton) findViewById(R.id.Button_date_income);

        Intent intent = getIntent();
        int category = intent.getIntExtra("kategorie", 0);
        String extraName3 = intent.getStringExtra("income_text2");

        repeat = (CheckBox) findViewById(R.id.checkBox_repeat_income);
        calendar= Calendar.getInstance();

        //Anzeige des richtigen Bildes und Textes
        switch(category) {
            case 1:
                imgView = (ImageView) findViewById(R.id.imageView_awards);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_awards);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgView = (ImageView) findViewById(R.id.imageView_investmentReturns);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_investmentReturns);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgView = (ImageView) findViewById(R.id.imageView_salary);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_salary);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgView = (ImageView) findViewById(R.id.imageView_dividend);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_dividend);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgView = (ImageView) findViewById(R.id.imageView_gambling);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_gambling);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 6:
                imgView = (ImageView) findViewById(R.id.imageView_refund);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_refunds);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 7:
                imgView = (ImageView) findViewById(R.id.imageView_extraCat_income);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_extraCat_income);
                txtView.setText(extraName3);
                txtView.setVisibility(View.VISIBLE);
                break;
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(new_income.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        textView.setText(mDay +"."+(mMonth+1)+"."+mYear);
                        day = mDay;
                        month = mMonth+1;
                        year = mYear;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton checkButton = (ImageButton) findViewById(R.id.Button_check_income);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Betrag abspeichern
                EditText add = (EditText) findViewById(R.id.editText_value_income);
                int addNumber = Integer.parseInt(add.getText().toString());

                //kategorie speichern
                String category = txtView.getText().toString();

                //Beschreibung speichern
                EditText descriptionText = (EditText) findViewById(R.id.editText_description_income);
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

                Intent intent= new Intent(new_income.this, MainActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.Button_cancel_income);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(new_income.this, income_menu.class);
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