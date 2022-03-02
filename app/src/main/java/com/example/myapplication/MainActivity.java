package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btn_login;
    public TextView fieldEmail, fieldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init vars
        btn_login = (Button) findViewById(R.id.btn_login);
        fieldEmail = (TextView) findViewById(R.id.fieldEmail);
        fieldPassword = (TextView) findViewById(R.id.fieldPassword);

        btn_login.setOnClickListener(btnListener);

    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {

                if(fieldEmail.getText().toString().equals("anliben@anliben.com") && fieldPassword.getText().toString().equals("1234")){
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Welcome "+fieldEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
        }
    };

}