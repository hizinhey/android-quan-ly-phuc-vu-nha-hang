package com.example.managerapp.models.modelsDAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class BuaAnDAO {
    private static final String FLAG = "BuaAn";

    public BuaAnDAO() {}

    public Task getAll() {
        /* Lấy tất cả danh sách khuyến mãi */
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection(FLAG).get();
    }

    public Task get(String id) {
        /* Lấy một khuyến mãi theo id */
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(FLAG).document(id);
        return docRef.get();
    }

    public Task set(String document, BuaAnEntity buaAnEntity) {
        /* Lấy một khuyến mãi theo id */
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(FLAG).document(document);
        return docRef.set(buaAnEntity);
    }

    public Task delete(String document) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(FLAG).document(document);
        return docRef.delete();
    }
}
