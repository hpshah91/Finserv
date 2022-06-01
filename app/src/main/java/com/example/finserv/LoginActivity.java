package com.example.finserv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finserv.helperclass.Constants;
import com.example.finserv.helperclass.ToastIntentClass;
import com.example.finserv.userdashboard.UserDashboardActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button signup_activity, login, forgotPassword;
    TextInputLayout email, password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        sp = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);

        email = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        signup_activity = findViewById(R.id.signin_activity);
        forgotPassword = findViewById(R.id.forgot_password_activity);
        login = findViewById(R.id.loginBtn);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ToastIntentClass(LoginActivity.this, ForgotPasswordActivity.class);
            }
        });




        signup_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(v);
            }
        });
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void checkUser(View view) {

        if (!validatePassword() | !validateEmail()) {
            return;
        } else {
            if(email.getEditText().getText().toString().equals("admin@gmail.com")&&password.getEditText().getText().toString().equals("admin123")){
                sp.edit().putString(Constants.ID, "1").apply();
                sp.edit().putString(Constants.NAME, "admin").apply();
                sp.edit().putString(Constants.EMAIL, email.getEditText().getText().toString()).apply();
                sp.edit().putString(Constants.PASSWORD, password.getEditText().getText().toString()).apply();
                sp.edit().putString(Constants.ACC_CASH, "0").apply();
                new ToastIntentClass(LoginActivity.this, UserDashboardActivity.class);
            }else{
                new ToastIntentClass(LoginActivity.this, "Invalid Username or Password");
            }
        }

    }
}