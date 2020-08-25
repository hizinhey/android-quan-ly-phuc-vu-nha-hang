package com.example.quanlyphucvunhahang.models.modelsDAO;

import com.example.quanlyphucvunhahang.models.modelsEntity.LichSuEntity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LichSuDAO {
    private static final String FLAG = "LichSu";
    private static final String SIMPLE = "LS";

    public LichSuDAO(){}

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
    public Task add(LichSuEntity record){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection(FLAG).add(record);
    }

    public Task change(LichSuEntity newRecord){
        /* Cập nhật khuyến mãi có id trùng với newRecord, bằng newRecord */
        return FirebaseFirestore.getInstance().collection(FLAG).document(newRecord.getKhachHang().getID()).set(newRecord);
    }

    public Task delete(String id) {
        return FirebaseFirestore.getInstance().collection(FLAG).document(id).delete();
    }
}
