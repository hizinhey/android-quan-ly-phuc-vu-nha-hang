package com.example.quanlyphucvunhahang.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlyphucvunhahang.MainActivity;
import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsDAO.LichSuDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.TaiKhoanDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.concurrent.Executor;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends Activity {
    TextInputLayout user;
    TextInputLayout pass;
    Button Login;
    CheckedTextView Signin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.textInputLayout);
        pass = findViewById(R.id.textInputLayout2);
        Login = findViewById(R.id.button);
        Signin = findViewById(R.id.checkedTextView);

        mAuth = FirebaseAuth.getInstance();

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getEditText().getText().toString().trim();
                String password = pass.getEditText().getText().toString().trim();

                if(username.equals("") || !username.contains("@") || password.length() < 6){
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
                    final TaiKhoanDAO dao = new TaiKhoanDAO();

                    final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.show();

                    // Xử lí đăng nhập
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        final String id = user.getUid();
                                        System.out.println(id);

                                        dao.get(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot o) {
                                                TaiKhoanEntity taiKhoanEntity = o.toObject(TaiKhoanEntity.class);
                                                System.out.println(taiKhoanEntity.getEmail());
                                                if(taiKhoanEntity != null && taiKhoanEntity.getPhanQuyen() == 2){
                                                    Intent intent = new Intent(getApplication(), HomeActivity.class);
                                                    intent.putExtra("USER", id);

                                                    startActivity(intent);
                                                } else {
                                                    mAuth.signOut();
                                                }
                                                pd.dismiss();
                                            }
                                        });

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại",
                                                Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    }
                                }
                            });
                }
            }
        });
    }
}
