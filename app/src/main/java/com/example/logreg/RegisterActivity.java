package com.example.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText uname, fname, email, pwd;
    private Button reg, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RegisterActivity.this, RegisterActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userame = uname.getText().toString();
                String fullname = fname.getText().toString();
                String Email = email.getText().toString();
                String Pwd = pwd.getText().toString();
                if(userame.isEmpty() || fullname.isEmpty() || Email.isEmpty() || Pwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
                    return;
                }

                adatfelvetel(Email, userame, Pwd, fullname);
                Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void adatfelvetel(String email, String uname, String password, String fullname){
        Connection dbHelper = new Connection(RegisterActivity.this);
        if(dbHelper.AdatFelvetel(email, uname,password,fullname)){
            Toast.makeText(this, "Sikeres felvétel!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(this, "Sikertelen felvétel!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void init() {
        uname = findViewById(R.id.etRegUname);
        fname = findViewById(R.id.etRegFname);
        email = findViewById(R.id.etRegEmail);
        pwd = findViewById(R.id.etRegPassword);

        reg = findViewById(R.id.btnRegReg);
        back = findViewById(R.id.btnBack);
    }
}
