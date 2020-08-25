package com.example.managerapp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.R;
import com.example.managerapp.adapters.DeleteRecyclerViewAdapter;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.example.managerapp.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeleteFragment extends Fragment {
    RecyclerView rcDelete;
    RecyclerView.LayoutManager layoutManager;
    DeleteRecyclerViewAdapter adapter;

    HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        homeViewModel.getmListMonAn().observe(this, new Observer<List<MonAnEntity>>() {
            @Override
            public void onChanged(@Nullable List<MonAnEntity> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                System.out.println(list.size());
                adapter = new DeleteRecyclerViewAdapter(getContext(), list);
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
}
