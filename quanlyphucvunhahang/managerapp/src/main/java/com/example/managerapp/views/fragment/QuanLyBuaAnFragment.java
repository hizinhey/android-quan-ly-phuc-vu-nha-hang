package com.example.managerapp.views.fragment;

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

import com.example.managerapp.R;
import com.example.managerapp.adapters.BuaAnRecyclerViewAdapter;
import com.example.managerapp.adapters.DeleteRecyclerViewAdapter;
import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.example.managerapp.viewmodels.HomeViewModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class QuanLyBuaAnFragment extends Fragment {
    RecyclerView rcDelete;
    RecyclerView.LayoutManager layoutManager;
    BuaAnRecyclerViewAdapter adapter;

    HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        homeViewModel.getmListBuaAn().observe(this, new Observer<List<BuaAnEntity>>() {
            @Override
            public void onChanged(@Nullable List<BuaAnEntity> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                adapter = new BuaAnRecyclerViewAdapter(getContext(), list);
                rcDelete.setAdapter(adapter);
            };
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        rcDelete = view.findViewById(R.id.rc_delete);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rcDelete.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("BuaAn").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                List<BuaAnEntity> buaAnEntities = new ArrayList<>();
                if(value != null){
                    for (QueryDocumentSnapshot doc : value) {
                        buaAnEntities.add(doc.toObject(BuaAnEntity.class));
                    }
                }

                homeViewModel.setmListBuaAn(buaAnEntities);
            }
        });
    }
}
