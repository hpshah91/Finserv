package com.example.finserv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finserv.helperclass.Constants;
import com.example.finserv.helperclass.ToastIntentClass;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sp;
    Button logout;
    TextView fullname, username;
    ImageView edit_profile;
    String sStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sp = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);

        fullname = findViewById(R.id.fullname_field);
        username = findViewById(R.id.username_field);
        edit_profile = findViewById(R.id.edit_profile_iv);
        logout = findViewById(R.id.logout_profile);

        new ToastIntentClass(ProfileActivity.this, sp.getString(Constants.NAME, ""));


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ToastIntentClass(ProfileActivity.this, EditProfileActivity.class);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().clear().apply();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}