package com.example.quanlyphucvunhahang.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.adapters.ListMonAnAdapter;
import com.example.quanlyphucvunhahang.models.modelAdapter.MonAnItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<MonAnItemAdapter> listData1;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khai vi
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

        listData1 = new ArrayList<>();
        listData1.add(new MonAnItemAdapter("a", "a"));
        listData1.add(new MonAnItemAdapter("b", "a"));
        listData1.add(new MonAnItemAdapter("a", "a"));
        listData1.add(new MonAnItemAdapter("a", "a"));
        listData1.add(new MonAnItemAdapter("a", "a"));

        //TODO: Sữa listData
        mAdapter1 = new ListMonAnAdapter(getContext(), listData1);
        recyclerView1.setAdapter(mAdapter1);
        mAdapter2 = new ListMonAnAdapter(getContext(), listData1);
        recyclerView2.setAdapter(mAdapter1);
        mAdapter3 = new ListMonAnAdapter(getContext(), listData1);
        recyclerView3.setAdapter(mAdapter1);
        mAdapter4 = new ListMonAnAdapter(getContext(), listData1);
        recyclerView4.setAdapter(mAdapter1);

        return view;
    }
}
