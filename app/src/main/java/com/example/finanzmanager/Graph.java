package com.example.finanzmanager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class Graph extends AppCompatActivity {
    int income = MainActivity.incomeSum;
    int expense= MainActivity.expenseSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        drawPie();

        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void drawPie(){


        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();

        if (income==0&&expense==0){
            config.startAngle(90)
                    .addData(new SimplePieInfo(50, Color.parseColor("#FF800000"), "Einnahmen"))
                    .addData(new SimplePieInfo(50, Color.parseColor("#FFFF00FF"), "Ausgaben"))
                    .drawText(true).strokeMode(false)
                    .duration(1000).textSize(40);// draw pie animation duration
        }else {
            config.startAngle(-90)// Starting angle offset
                    .addData(new SimplePieInfo(income, Color.parseColor("#FF800000"), "1 Test"))
                    .addData(new SimplePieInfo(expense, Color.parseColor("#FFFF00FF"), "2 Test"))
                    .drawText(true).strokeMode(false)
                    .duration(1000).textSize(40);// draw pie animation duration
        }
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

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
