package com.example.managerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.managerapp.views.HomeActivity;
import com.example.managerapp.views.LoginActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.saved_user_status), "");
        if (user.equals("")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        }
    }
}
