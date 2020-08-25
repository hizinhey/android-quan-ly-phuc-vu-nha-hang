package com.example.quanlyphucvunhahang.models.modelsDAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class MonAnDAO {
    private static final String FLAG = "MonAn";
    private static final String SIMPLE = "MA";

    public MonAnDAO(){}

    public Task getAll() {
        /* Lấy tất cả danh sách khuyến mãi */
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection(FLAG).get();
    }

    public Task get(String id) {
        /* Lấy một khuyến mãi theo id */
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(FLAG).document(id);
        return docRef.get();
    }

    // Khuyen mai chi set, khong can kiem tra da ton tai khuyen mai nay hay chua
    public Task add(MonAnEntity record){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection(FLAG).add(record);
    }

    public Task change(MonAnEntity newRecord){
        /* Cập nhật khuyến mãi có id trùng với newRecord, bằng newRecord */
        return FirebaseFirestore.getInstance().collection(FLAG).document(newRecord.getID()).set(newRecord);
    }

    public int delete(String id) {

        return 0;
    }
}
