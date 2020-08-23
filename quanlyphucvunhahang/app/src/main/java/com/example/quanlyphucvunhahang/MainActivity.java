package com.example.quanlyphucvunhahang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quanlyphucvunhahang.helpers.ExampleEntity;
import com.example.quanlyphucvunhahang.models.modelsDAO.KhuyenMaiDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.MonAnDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.viewmodels.UserViewModel;
import com.example.quanlyphucvunhahang.views.HomeActivity;
import com.example.quanlyphucvunhahang.views.LoginActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.TreeMap;

public class MainActivity extends Activity {
    Button btnTest;
    KhuyenMaiDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.saved_user_status), "");
        if(user.equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        }


        btnTest = findViewById(R.id.btn_test);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Test
            }
        });
    }
}
