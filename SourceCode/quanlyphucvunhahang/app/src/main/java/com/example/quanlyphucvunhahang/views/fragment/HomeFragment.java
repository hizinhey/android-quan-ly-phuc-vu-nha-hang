package com.example.quanlyphucvunhahang.views.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.adapters.ListMonAnAdapter;
import com.example.quanlyphucvunhahang.adapters.CustomPageAdapter;
import com.example.quanlyphucvunhahang.models.modelsDAO.KhuyenMaiDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private RecyclerView.Adapter mAdapter1;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.Adapter mAdapter3;
    private RecyclerView.Adapter mAdapter4;
    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.LayoutManager layoutManager2;
    private RecyclerView.LayoutManager layoutManager3;
    private RecyclerView.LayoutManager layoutManager4;

    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        final String taiKhoan = homeViewModel.getmTaiKhoan().getValue().getID();
        System.out.println(taiKhoan);

        homeViewModel.getmListMonAnKhaiVi().observe(this, new Observer<List<MonAnEntity>>() {
            @Override
            public void onChanged(@Nullable List<MonAnEntity> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                mAdapter1 = new ListMonAnAdapter(getContext(), list, taiKhoan);
                recyclerView1.setAdapter(mAdapter1);
            };
        });

        homeViewModel.getmListMonAnMonChinh().observe(this, new Observer<List<MonAnEntity>>() {
            @Override
            public void onChanged(@Nullable List<MonAnEntity> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }

                mAdapter2 = new ListMonAnAdapter(getContext(), list, taiKhoan);
                recyclerView2.setAdapter(mAdapter2);
            };
        });

        homeViewModel.getmListMonAnTrangMieng().observe(this, new Observer<List<MonAnEntity>>() {
            @Override
            public void onChanged(@Nullable List<MonAnEntity> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }

                mAdapter3 = new ListMonAnAdapter(getContext(), list, taiKhoan);
                recyclerView3.setAdapter(mAdapter3);
            };
        });

        homeViewModel.getmListMonAnMonNuoc().observe(this, new Observer<List<MonAnEntity>>() {
            @Override
            public void onChanged(@Nullable List<MonAnEntity> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }

                mAdapter4 = new ListMonAnAdapter(getContext(), list, taiKhoan);
                recyclerView4.setAdapter(mAdapter4);
            };
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // ViewPager
        viewPager = view.findViewById(R.id.viewPagerHome);
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.getAll().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> listURL = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listURL.add(document.toObject(KhuyenMaiEntity.class).getStrHinhAnh());
                    }
                    if (viewPager != null) {
                        viewPager.setAdapter(new CustomPageAdapter(getContext(), listURL));
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // RC
        recyclerView1 = view.findViewById(R.id.rcKhaiVi);
        recyclerView1.setHasFixedSize(true);
        recyclerView2 = view.findViewById(R.id.rcMonChinh);
        recyclerView2.setHasFixedSize(true);
        recyclerView3 = view.findViewById(R.id.rcTrangMieng);
        recyclerView3.setHasFixedSize(true);
        recyclerView4 = view.findViewById(R.id.rcMonNuoc);
        recyclerView4.setHasFixedSize(true);

        layoutManager1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager3 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager4 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView4.setLayoutManager(layoutManager4);

        recyclerView1.setAdapter(mAdapter1);
        recyclerView2.setAdapter(mAdapter2);
        recyclerView3.setAdapter(mAdapter3);
        recyclerView4.setAdapter(mAdapter4);
    }
}
