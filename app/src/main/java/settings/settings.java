package settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.crashlytics.android.Crashlytics;
import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.EmailPasswordActivity;
import com.example.finanzmanager.activity_classes.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

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

        ImageButton log_out = (ImageButton) findViewById(R.id.imageButton_logOut);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(settings.this, EmailPasswordActivity.class);
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

        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

    }

}
