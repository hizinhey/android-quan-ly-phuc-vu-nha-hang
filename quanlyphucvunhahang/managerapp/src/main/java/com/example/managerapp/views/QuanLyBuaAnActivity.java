package com.example.managerapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.R;
import com.example.managerapp.adapters.DeleteRecyclerViewAdapter;
import com.example.managerapp.adapters.MonAnRecyclerViewAdapter;
import com.example.managerapp.models.modelsDAO.BuaAnDAO;
import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class QuanLyBuaAnActivity extends AppCompatActivity {
    RecyclerView rcDelete;
    RecyclerView.LayoutManager layoutManager;
    MonAnRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_delete);

        rcDelete = findViewById(R.id.rc_delete);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcDelete.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        String buaAn = intent.getStringExtra("BuaAn");

        BuaAnDAO dao = new BuaAnDAO();
        dao.get(buaAn).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        BuaAnEntity buaAnEntity = document.toObject(BuaAnEntity.class);
                        adapter = new MonAnRecyclerViewAdapter(getApplication(), buaAnEntity.getListChiTietBuaAn(), buaAnEntity.getID());
                        rcDelete.setAdapter(adapter);
                    }
                }
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("BuaAn").document(buaAn);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    BuaAnEntity buaAnEntity = snapshot.toObject(BuaAnEntity.class);
                    adapter = new MonAnRecyclerViewAdapter(getApplication(), buaAnEntity.getListChiTietBuaAn(), buaAnEntity.getID());
                    rcDelete.setAdapter(adapter);
                }
            }
        });

    }
}
