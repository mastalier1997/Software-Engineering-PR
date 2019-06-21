package addNew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finanzmanager.R;

import java.io.IOException;

public class create_category_expense extends AppCompatActivity {

    ImageButton selectImage;
    ImageButton check;
    ImageButton cancel;
    private int PICK_IMAGE_REQUEST = 1;
    static int id = 10;
    //gespeicherte Variablen
    static String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category_expense);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Kategorie erstellen </font>"));


        check = (ImageButton) findViewById(R.id.Button_create_category_expense);
        EditText text = findViewById(R.id.textView_newCatName_expense);
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
                TextView textView = findViewById(R.id.infoName2);
                textView.setVisibility(View.INVISIBLE);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (text.getText().toString()){
                    case "":break;
                    default:EditText content = findViewById(R.id.textView_newCatName_expense);
                        name = content.getText().toString();

                        Intent intent= new Intent(create_category_expense.this, expense_menu.class);
                        intent.putExtra("eins", name);
                        startActivity(intent);
                }

            }
        });

        cancel = (ImageButton) findViewById(R.id.Button_cancel_category_expense);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(create_category_expense.this, expense_menu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                selectImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent intent = new Intent(create_category_expense.this, expense_menu.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
