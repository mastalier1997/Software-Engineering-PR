package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.finanzmanager.R;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageButton repeat_settings = (ImageButton) findViewById(R.id.button_repeat_settings);
        repeat_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(settings.this, repeat_settings_income.class);
                startActivity(intent);
            }
        });

        ImageButton delete_date = (ImageButton) findViewById(R.id.button_delete_Data);
        delete_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.deleteData();
                Intent intent = new Intent(settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
