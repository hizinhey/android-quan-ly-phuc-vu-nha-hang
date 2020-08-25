package com.example.quanlyphucvunhahang.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsDAO.TaiKhoanDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {
    TextInputLayout email;
    TextInputLayout pass;
    TextInputLayout name;
    TextInputLayout address;
    TextInputLayout phone;
    Button btnRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        connectWidget();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String semail = email.getEditText().getText().toString();
                final String spass= pass.getEditText().getText().toString();
                final String sname = name.getEditText().getText().toString();
                final String saddress = address.getEditText().getText().toString();
                final String sphone = phone.getEditText().getText().toString();

                if(!semail.contains("@") || spass.length() < 6 || sname.equals("") || saddress.equals("") || sphone.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                    alertDialog.setTitle("Thông báo");
                    alertDialog.setMessage("Dữ liệu nhập có vấn đề, kiểm tra lại.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.show();
                    mAuth.createUserWithEmailAndPassword(semail, spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplication(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                System.out.println(task.getResult().getUser().getUid());

                                TaiKhoanDAO dao = new TaiKhoanDAO();
                                TaiKhoanEntity user = new TaiKhoanEntity(
                                        task.getResult().getUser().getUid(),
                                        sname,
                                        semail,
                                        sphone,
                                        saddress,
                                        2);
                                try {
                                    dao.set(user);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                pd.dismiss();
                                onBackPressed();
                            } else {
                                Toast.makeText(getApplication(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }

    private void connectWidget(){
        email = findViewById(R.id.textInputLayout);
        pass = findViewById(R.id.textInputLayout2);
        name = findViewById(R.id.textInputLayout3);
        address = findViewById(R.id.textInputLayout4);
        phone = findViewById(R.id.textInputLayout5);
        btnRegister = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
    }
}
