package com.example.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoggedInActivity extends AppCompatActivity {

    private TextView name;
    private EditText mainactivityname;
    private Button btnVissza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();
        kiir();

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(LoggedInActivity.this, RegisterActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void kiir(){
        Connection nev = new Connection(LoggedInActivity.this);
        String fnev = nev.adatlekerdezes(mainactivityname.toString());
        name.setText(fnev);
    }

    private void init() {
        name = findViewById(R.id.user_name);
        btnVissza = findViewById(R.id.btnKijelentkezes);
        mainactivityname = findViewById(R.id.etUname);
    }
}
