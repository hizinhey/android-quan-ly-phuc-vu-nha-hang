package com.example.quanlyphucvunhahang.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.quanlyphucvunhahang.GlideApp;
import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsDAO.BuaAnDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.MonAnDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.BuaAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietBuaAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChiTietMonAnActivity extends AppCompatActivity {
    ImageView imageMonAn;
    TextView lblTenMonAn;
    TextView lblNhomMon;
    TextView lblMoTa;
    TextView lblGia;
    Button btnAddToBuaAn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_monan);

        setDataToView();


    }

    public void setDataToView() {
        // Connect
        imageMonAn = findViewById(R.id.imgDetailMonAn);
        lblTenMonAn = findViewById(R.id.lblDetailMonAnTitle);
        lblNhomMon = findViewById(R.id.lblDetailMonAnNhomMonAn);
        lblMoTa = findViewById(R.id.lblDetailMonAnMota);
        lblGia = findViewById(R.id.lblDetailMonAnGia);
        btnAddToBuaAn = findViewById(R.id.btnDetailMonAnAdd);

        Intent intent = getIntent();
        final String idMonAn = intent.getStringExtra("MonAn");
        final String idUser = intent.getStringExtra("TaiKhoan");


        MonAnDAO dao = new MonAnDAO();
        dao.get(idMonAn).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                MonAnEntity monAnEntity = documentSnapshot.toObject(MonAnEntity.class);

                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(monAnEntity.getLinkHinhAnh());

                GlideApp.with(getApplication())
                        .load(storageReference)
                        .into(imageMonAn);

                lblTenMonAn.setText(monAnEntity.getTenMonAn());
                lblMoTa.setText("- Mô tả: " + monAnEntity.getMoTa());
                lblGia.setText(monAnEntity.getGia().toString() + " $");
                lblNhomMon.setText("- Nhóm món: " + monAnEntity.getNhomMonAn());

            }
        });

        btnAddToBuaAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BuaAnDAO dao = new BuaAnDAO();
                dao.get(idUser).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                final BuaAnEntity buaAnEntity = document.toObject(BuaAnEntity.class);
                                new MonAnDAO().get(idMonAn).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        MonAnEntity monAnEntity = documentSnapshot.toObject(MonAnEntity.class);
                                        buaAnEntity.getListChiTietBuaAn().add(new ChiTietBuaAn(monAnEntity, 0));
                                        dao.set(idUser, buaAnEntity);
                                        Toast.makeText(getApplication(), "Thêm món ăn thành công", Toast.LENGTH_LONG).show();
                                    }
                                });

                            } else {
                                Toast.makeText(getApplication(), "Chưa tạo bữa ăn.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplication(), "Thêm món ăn thất bại.", Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });
    }

    ;
}
