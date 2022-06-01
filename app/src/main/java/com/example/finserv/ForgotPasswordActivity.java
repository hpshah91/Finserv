package com.example.finserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finserv.helperclass.ToastIntentClass;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgetPassword = findViewById(R.id.forgot_password_btn);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ToastIntentClass(ForgotPasswordActivity.this,LoginActivity.class);
            }
        });
    }
}