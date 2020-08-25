package com.example.managerapp.views.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.managerapp.GlideApp;
import com.example.managerapp.R;
import com.example.managerapp.models.modelsDAO.MonAnDAO;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class AddFragment extends Fragment {
    TextInputLayout edtMoTa;
    TextInputLayout tilTen;
    TextInputLayout tilGia;
    CardView cardView;
    ImageView imageView;
    Button button;
    RadioGroup radioGroup;
    String selectedImagePath;

    String strImg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        edtMoTa = view.findViewById(R.id.add_moTa);
        tilGia = view.findViewById(R.id.add_gia);
        tilTen = view.findViewById(R.id.add_tenMonAn);
        cardView = view.findViewById(R.id.add_cardview);
        imageView = view.findViewById(R.id.add_img);
        button = view.findViewById(R.id.add_button);
        radioGroup = view.findViewById(R.id.add_radioGroup);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), 1);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = tilTen.getEditText().getText().toString();
                final String gia = tilGia.getEditText().getText().toString();
                final String mota = edtMoTa.getEditText().getText().toString();
                int radio = radioGroup.getCheckedRadioButtonId();
                if(radio == -1){
                    Toast.makeText(getActivity(), "Chưa chọn nhóm món ăn", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton radioButton = getView().findViewById(radioGroup.getCheckedRadioButtonId());
                final String nhommonan = (String) radioButton.getText();

                String idPicture = new Date().getTime() + "";
                final String link = "MonAn/"+idPicture+".jpg";
                // Get the data from an ImageView as bytes
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference pictureRef = storageRef.child(link);

                UploadTask uploadTask = pictureRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final MonAnDAO dao = new MonAnDAO();
                        final MonAnEntity monAnEntity = new MonAnEntity(
                                null,
                                Float.parseFloat(gia),
                                ten,
                                mota,
                                null,
                                nhommonan,
                                link
                        );
                        dao.add(monAnEntity).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                DocumentReference documentSnapshot = (DocumentReference) task.getResult();
                                monAnEntity.setID(documentSnapshot.getId());
                                dao.change(monAnEntity);
                                Toast.makeText(getActivity(), "Thêm món ăn thành công.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = selectedImageUri.getPath();

                System.out.println(selectedImagePath);

                GlideApp.with(getContext()).load(selectedImageUri).into(imageView);
            }
        }
    }

}
