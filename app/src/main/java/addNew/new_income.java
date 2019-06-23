package addNew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.MainActivity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class new_income extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    ImageView imgView;
    TextView txtView;
    CheckBox repeat;
    TextView textView;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH)+1;
    int year = calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                month=month-1;
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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Einnahme </font>"));


        ImageButton checkButton = (ImageButton) findViewById(R.id.Button_check_income);
        EditText add = (EditText) findViewById(R.id.editText_value_income);
        add.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6,2)});
        checkButton.setEnabled(false);

        //checkt ob Input im Feld geschehen ist
        add.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().equals("")){

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    checkButton.setEnabled(false);
                }else {
                    checkButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView textView = findViewById(R.id.hinweisBetrag2);
                textView.setVisibility(View.INVISIBLE);
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (add.getText().toString()){
                        case "":break;
                        default://Betrag abspeichern
                            double addNumber = Double.parseDouble(add.getText().toString());

                            //kategorie speichern
                            String category = txtView.getText().toString();

                            //Beschreibung speichern
                            EditText descriptionText = (EditText) findViewById(R.id.editText_description_income);
                            String description = descriptionText.getText().toString();

                            //wiederkehrend
                            Boolean repeats = false;
                            if (repeat.isChecked()) repeats = true;

                            //alles in einem Bundle gespeichert
                            Bundle extras = new Bundle();
                            extras.putDouble("value", addNumber);
                            extras.putInt("day", day);
                            extras.putInt("month", month);
                            extras.putInt("year", year);
                            extras.putString("category", category);
                            extras.putString("description", description);
                            extras.putBoolean("repeats", repeats);
                            extras.putString("type", "income");

                            Intent intent = new Intent(new_income.this, MainActivity.class);
                            intent.putExtras(extras);
                            startActivity(intent);

                    }



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

    private class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
            mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }

    }
}