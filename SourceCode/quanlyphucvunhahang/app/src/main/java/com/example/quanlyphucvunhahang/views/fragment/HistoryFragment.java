package com.example.quanlyphucvunhahang.views.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.adapters.HistoryAdapter;
import com.example.quanlyphucvunhahang.models.modelsEntity.BuaAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.LichSuEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoryAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        homeViewModel.getmLichSu().observe(this, new Observer<LichSuEntity>() {
            @Override
            public void onChanged(LichSuEntity lichSuEntity) {
                mAdapter = new HistoryAdapter(getContext(), lichSuEntity.getChiTiet());
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.rcHistory);
        layoutManager= new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("LichSu").document(homeViewModel.getmTaiKhoan().getValue().getID());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    homeViewModel.setmLichSu(snapshot.toObject(LichSuEntity.class));
                } else {
                    homeViewModel.setmLichSu(null);
                }
            }
        });
    }
}
