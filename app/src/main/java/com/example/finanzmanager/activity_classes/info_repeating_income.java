package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;

import com.example.finanzmanager.R;

import org.w3c.dom.Text;

public class info_repeating_income extends AppCompatActivity {

    EditText description;
    EditText value;
    String old_desc;
    double old_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_repeating_income);

        description = (EditText) findViewById(R.id.editText_info_description_income);
        value = (EditText) findViewById(R.id.editText_info_value_income);

        Intent intent = getIntent();
        double valueText = intent.getDoubleExtra("value", 0);
        String descriptionText = intent.getStringExtra("description");

        old_desc = descriptionText;
        old_value = valueText;

        description.setText(descriptionText);
        value.setText(String.valueOf(valueText));

        Button save = (Button) findViewById(R.id.button_save_info_income);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_desc = description.getText().toString();
                double new_value = Double.parseDouble(value.getText().toString());

                MainActivity.account.changeRepeatingIncomeList(old_desc, old_value, new_desc, new_value);
                MainActivity.saveToAccount();

                Intent intent= new Intent(info_repeating_income.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

}
