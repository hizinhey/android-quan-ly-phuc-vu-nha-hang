package com.example.quanlyphucvunhahang.views;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.example.quanlyphucvunhahang.R;

public class ThemMonAnActivity extends Activity {

    EditText editTen, editNhom, editGia, editMoTa;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);

        editTen = (EditText) findViewById(R.id.editText1);
        editNhom = (EditText) findViewById(R.id.editText2);
        editGia = (EditText) findViewById(R.id.editText3);
        editMoTa = (EditText) findViewById(R.id.editText4);
        String ten = editTen.getText().toString();
        String nhom = editNhom.getText().toString();
        String strGia = editGia.getText().toString();
        int gia = setIntValue(strGia);
        String moTa = editMoTa.getText().toString();

        btnAdd = (Button) findViewById(R.id.button);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Test
            }
        });
    }

    int setIntValue(String str){
        if (!"".equals(str))
            return Integer.parseInt(str);
        return 0;
    }
}
