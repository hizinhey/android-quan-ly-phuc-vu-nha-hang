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
    public static String newID = null;
    private final int[] result = {0, 0, 0};

    public MonAnDAO() {
        getNewID();
    }

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

    // Khuyen mai chi set, khong can kiem tra da ton tai khuyen mai nay hay chua
    public int set(final MonAnEntity record) throws InterruptedException {
        /* Thêm một khuyến mãi record */
        result[0] = 0;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task task = db.collection(FLAG)
                .document(record.getID())
                .set(record)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        result[0] = 1;
                        getNewID();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        result[0] = 0;
                    }
                });

        // Kiem tra task da hoan tat chua
        int i = 0;
        while (!task.isComplete() && !task.isCanceled()) {
            i++;
            Thread.sleep(1);
            if (i == 3000) {
                result[0] = 2;
                break;
            }
        }
        return result[0];
    }

    public int change(MonAnEntity newRecord) throws InterruptedException {
        /* Cập nhật khuyến mãi có id trùng với newRecord, bằng newRecord */
        return set(newRecord);
    }

    public int delete(String id) throws InterruptedException {
        /* Xóa một khuyến mãi theo id */
        result[1] = 0;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task task = db.collection(FLAG)
                .document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        result[1] = 1;
                        getNewID();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        result[1] = 0;
                    }
                });

        // Kiem tra task da hoan tat chua
        int i = 0;
        while (!task.isComplete() && !task.isCanceled()) {
            i++;
            Thread.sleep(1);
            if (i == 3000) {
                result[1] = 2;
                break;
            }
        }
        return result[1];
    }

    // Gắn vào biến newID giá trị mới nhất
    public void getNewID() {
        /*Tìm ID cuối cùng và cộng 1 vào để tạo ID mới*/
        getAll().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Integer maxId = (newID == null)?1:Integer.parseInt(newID.substring(2));
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String temp = document.getId().substring(2);
                        Integer tempi = Integer.parseInt(temp);
                        if(tempi > maxId){
                            maxId = tempi;
                        }
                    }

                    newID = createStringIdFromInteger(maxId + 1);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private String createStringIdFromInteger(Integer iID){
        String res = SIMPLE;
        String strNumber = iID.toString();
        StringBuilder createZeros = new StringBuilder();
        for(int i = 0; i < 10 - strNumber.length(); i++){
            createZeros.append('0');
        }

        return res + createZeros.toString() + strNumber;
    }
}
