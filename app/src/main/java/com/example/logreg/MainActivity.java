package com.example.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regActivityre = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regActivityre);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pwd = password.getText().toString();

                Connection login = new Connection( MainActivity.this);

                boolean bool = login.Login(uname, pwd);

                if( uname.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(MainActivity.this, "Minden adatot meg kell adni!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (bool == false){
                    Toast.makeText(MainActivity.this, "Hibás adat!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent logActivityre = new Intent(MainActivity.this, LoggedInActivity.class);
                    startActivity(logActivityre);
                    finish();
                }
            }
        });
    }

    private void init() {
        username = findViewById(R.id.etUname);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        reg = findViewById(R.id.btnReg);
    }
}
