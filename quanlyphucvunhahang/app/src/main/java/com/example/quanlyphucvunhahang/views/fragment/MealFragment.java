package com.example.quanlyphucvunhahang.views.fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.adapters.CustomPageAdapter;
import com.example.quanlyphucvunhahang.adapters.ListMonAnLongApdater;
import com.example.quanlyphucvunhahang.models.modelsDAO.BanAnDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.BuaAnDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.BanAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.BuaAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietBuaAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MealFragment extends Fragment {
    TextView status;

    Button action;

    // Infor;
    TextView customer;
    TextView table;
    TextView subStatus;
    TextView payment;

    RecyclerView rcList;

    //data
    List<MonAnEntity> listMonAn;

    // Viewmodel
    HomeViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        mViewModel.getmBuaAn().observe(this, new Observer<BuaAnEntity>() {
            @Override
            public void onChanged(@Nullable final BuaAnEntity buaAn) {
                BuaAnEntity info;
                if (buaAn == null) {
                    info = new BuaAnEntity(
                            null,
                            "Không có bàn",
                            new ArrayList<ChiTietBuaAn>(),
                            0,
                            new TaiKhoanEntity(),
                            "",
                            0
                    );
                } else {
                    info = buaAn;
                }
                status.setText(info.strTrangThai());
                customer.setText("Khách hàng: " + ((info.getKhachHang() != null) ? info.getKhachHang().getTen() : "null"));
                table.setText("Bàn ăn: " + info.getBanAn());
                subStatus.setText("Trạng thái: " + info.strTrangThai());
                payment.setText("Hình thức thanh toán: " + info.strHinhThucThanhToan());

                System.out.println(info.getListChiTietBuaAn().size());
                ListMonAnLongApdater adapter = new ListMonAnLongApdater(getContext(), info.getListChiTietBuaAn());
                rcList.setHasFixedSize(true);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rcList.setLayoutManager(layoutManager);
                rcList.setAdapter(adapter);

                if (info.getTrangThai() == 1) {
                    action.setText("Kết thúc bữa ăn");
                    action.setEnabled(true);
                    action.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO: Gởi yêu cầu kết thúc
                        }
                    });
                } else if (info.getTrangThai() == 2) {
                    action.setText("Waiting ...");
                    action.setEnabled(false);
                } else {
                    action.setText("Tạo bữa ăn");
                    action.setEnabled(true);
                    action.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BanAnDAO dao = new BanAnDAO();
                            dao.getAll().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<String> list = new ArrayList<>();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            BanAnEntity banAnEntity = document.toObject(BanAnEntity.class);
                                            if (!banAnEntity.getIsBusy()) {
                                                list.add(banAnEntity.getId());
                                            }
                                        }
                                        final String[] str = list.toArray(new String[]{});
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                                        dialog.setTitle("Chọn bàn");
                                        dialog.setItems(str, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(final DialogInterface dialog, int position) {
                                                // TODO Auto-generated method stub
                                                final BuaAnEntity newBuaAn = new BuaAnEntity(
                                                        mViewModel.getmTaiKhoan().getValue().getID(),
                                                        //TODO: bàn ăn
                                                        str[position],
                                                        new ArrayList<ChiTietBuaAn>(),
                                                        0,
                                                        mViewModel.getmTaiKhoan().getValue(),
                                                        "",
                                                        1
                                                );

                                                final BuaAnDAO buaAnDAO = new BuaAnDAO();
                                                buaAnDAO.set(newBuaAn.getID(), newBuaAn).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(getContext(), "Tạo bữa ăn thành công", Toast.LENGTH_LONG).show();
                                                            buaAnDAO.get(newBuaAn.getID()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    BuaAnEntity buaAnEntity = documentSnapshot.toObject(BuaAnEntity.class);
                                                                    mViewModel.setmBuaAn(buaAnEntity);
                                                                }
                                                            });

                                                            dialog.dismiss();
                                                        } else{
                                                            Toast.makeText(getContext(), "Tạo bữa ăn thành công", Toast.LENGTH_LONG).show();
                                                            dialog.dismiss();
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // TODO Auto-generated method stub
                                                dialog.dismiss();
                                            }
                                        });
                                        AlertDialog alert = dialog.create();
                                        alert.show();
                                    } else {

                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal, container, false);

        status = view.findViewById(R.id.txtMealStatus);
        action = view.findViewById(R.id.btnMealAction);
        customer = view.findViewById(R.id.lblMealTenKhachHang);
        table = view.findViewById(R.id.lblMealBanAn);
        subStatus = view.findViewById(R.id.lblMealTrangThai);
        payment = view.findViewById(R.id.lblMealHinhThucThanhToan);

        rcList = view.findViewById(R.id.rcMealDanhSAchMonAn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("BuaAn").document(mViewModel.getmTaiKhoan().getValue().getID());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    mViewModel.setmBuaAn(snapshot.toObject(BuaAnEntity.class));
                } else {
                    mViewModel.setmBuaAn(null);
                }
            }
        });
    }
}
