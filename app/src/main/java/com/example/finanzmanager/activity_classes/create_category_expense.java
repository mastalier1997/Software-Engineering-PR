package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.finanzmanager.R;

import java.io.IOException;

public class create_category_expense extends AppCompatActivity {

    ImageButton selectImage;
    ImageButton check;
    private int PICK_IMAGE_REQUEST = 1;
    static int id = 10;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category_expense);

        selectImage = (ImageButton) findViewById(R.id.Button_selectImage_expense);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        check = (ImageButton) findViewById(R.id.Button_create_category_expense);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText content = findViewById(R.id.textView_newCatName_expense);
                name = content.getText().toString();
                Intent intent= new Intent(create_category_expense.this, expense_menu.class);
                intent.putExtra("Test", name);
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

}
