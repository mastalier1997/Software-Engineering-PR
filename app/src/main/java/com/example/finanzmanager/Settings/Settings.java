package com.example.finanzmanager.Settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.finanzmanager.R;
import com.example.finanzmanager.ActivityClasses.EmailPasswordActivity;
import com.example.finanzmanager.ActivityClasses.MainActivity;
import com.google.firebase.auth.FirebaseAuth;


public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='"+getResources().getColor(R.color.colorAccent)+"'>Einstellungen </font>"));


        setContentView(R.layout.activity_settings);

        ImageButton repeat_settings = (ImageButton) findViewById(R.id.button_repeat_settings);
        repeat_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Settings.this, RepeatSettingsIncome.class);
                startActivity(intent);
            }
        });

        ImageButton log_out = (ImageButton) findViewById(R.id.imageButton_logOut);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(Settings.this, EmailPasswordActivity.class);
                startActivity(intent);
            }
        });

        ImageButton delete_date = (ImageButton) findViewById(R.id.button_delete_Data);
        delete_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.deleteData();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    /**
     * back button to Main Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }

        return super.onOptionsItemSelected(item);
    }

}
