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

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout name, email, password;
    Button login_activity, signup;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        sp = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

        login_activity = findViewById(R.id.login_activity_btn);
        signup = findViewById(R.id.signin_btn);

        login_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
            }
        });
    }

    private void registerUser(View v) {
        if (!validateName() | !validatePassword() |  !validateEmail()) {
            return;
        }
        else {
            sp.edit().putString(Constants.ID, "1").apply();
            sp.edit().putString(Constants.NAME, name.getEditText().getText().toString()).apply();
            sp.edit().putString(Constants.EMAIL, email.getEditText().getText().toString()).apply();
            sp.edit().putString(Constants.PASSWORD, password.getEditText().getText().toString()).apply();
            new ToastIntentClass(SignUpActivity.this,"SignUp Successful");
            new ToastIntentClass(SignUpActivity.this, UserDashboardActivity.class);
            finish();
        }
    }

    private Boolean validateName() {
        String val = name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            name.setError("Username to long");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
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
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}