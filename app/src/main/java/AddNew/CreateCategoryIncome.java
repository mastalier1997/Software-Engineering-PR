package AddNew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.finanzmanager.R;


public class CreateCategoryIncome extends AppCompatActivity {

    ImageButton check;
    ImageButton cancel;
    String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category_income);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Kategorie erstellen </font>"));


        check = (ImageButton) findViewById(R.id.Button_create_category_income);
        EditText text = findViewById(R.id.textView_newCatName_income);
        check.setEnabled(false);


        //checkt ob Input im Feld geschehen ist
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    check.setEnabled(false);
                }else {
                    check.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView textView = findViewById(R.id.infoName);
                textView.setVisibility(View.INVISIBLE);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (text.getText().toString()){
                    case "":break;
                    default:
                        EditText content2 = findViewById(R.id.textView_newCatName_income);
                        name2 = content2.getText().toString();
                        Intent intent= new Intent(CreateCategoryIncome.this, IncomeMenu.class);
                        intent.putExtra("income_text", name2);
                        startActivity(intent);
                }


            }
        });

        cancel = (ImageButton) findViewById(R.id.Button_cancel_category_income);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CreateCategoryIncome.this, IncomeMenu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent intent = new Intent(CreateCategoryIncome.this, IncomeMenu.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
