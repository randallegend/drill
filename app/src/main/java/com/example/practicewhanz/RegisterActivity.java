package com.example.practicewhanz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.practicewhanz.DBHelper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText edUsername, edEmail, edPassword, edCPassword;
    Button registerBtn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = (TextInputEditText)findViewById(R.id.edUsername);
        edEmail = (TextInputEditText)findViewById(R.id.edEmail);
        edPassword = (TextInputEditText)findViewById(R.id.edPassword);
        edCPassword = (TextInputEditText)findViewById(R.id.edCpassword);
        registerBtn = (Button)findViewById(R.id.registerBtn);
        dbHelper = new DBHelper(this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = validate(edUsername.getText().toString(), edEmail.getText().toString(), edPassword.getText().toString(), edCPassword.getText().toString());
                if(valid){

                    Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }

    private boolean validate(String username, String email, String password, String cpassword){
        if(username.length() == 0){
            edUsername.requestFocus();
            edUsername.setError("Field must not be empty.");
            return false;
        }
        else if(email.length() == 0){
            edEmail.requestFocus();
            edEmail.setError("Field must not be empty.");
            return false;
        }
        else if(password.length() == 0){
            edPassword.requestFocus();
            edPassword.setError("Field must not be empty.");
            return false;
        }
        else if(!password.equals(cpassword)){
            edCPassword.requestFocus();
            edCPassword.setError("Field must be the same as password.");
            return false;
        }
        else{
            boolean register = dbHelper.registerUser(username, email, password);
            if(register)
                return true;
            else{
                Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

    }

}