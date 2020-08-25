package com.example.managerapp.models.modelsDAO;

import com.example.managerapp.models.modelsEntity.BanAnEntity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class BanAnDAO {
    private static final String FLAG = "BanAn";



    public Task getAll(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection(FLAG).get();
    }

    public Task add(BanAnEntity record){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection(FLAG).document(record.getId()).set(record);
    }
}
