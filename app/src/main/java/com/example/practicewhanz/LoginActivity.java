package com.example.practicewhanz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicewhanz.DBHelper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextInputEditText edEmail, edPassword;
    TextView registerBtn;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edEmail = (TextInputEditText)findViewById(R.id.edEmail);
        edPassword = (TextInputEditText)findViewById(R.id.edPassword);
        Button login = (Button)findViewById(R.id.loginBtn);
        registerBtn = (TextView)findViewById(R.id.registerBtn);
        dbHelper = new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate(edEmail.getText().toString(), edPassword.getText().toString())){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                }
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }

    private boolean validate(String email, String password){
        if(email.length() == 0){
            edEmail.requestFocus();
            edEmail.setError("Field must not be empty.");
            return false;
        }
        if(password.length() == 0){
            edPassword.requestFocus();
            edPassword.setError("Field must not be empty.");
            return false;
        }
        else{
            int login = dbHelper.loginUser(email, password);
            if(login > -1){
                return true;
            }else{
                Toast.makeText(LoginActivity.this, "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}