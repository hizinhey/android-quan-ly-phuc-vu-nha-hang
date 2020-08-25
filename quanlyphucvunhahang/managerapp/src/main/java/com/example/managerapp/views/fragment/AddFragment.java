package com.example.managerapp.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.managerapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddFragment extends Fragment {
    TextInputLayout edtMoTa;
    TextInputLayout tilTen;
    TextInputLayout tilGia;
    CardView cardView;
    ImageView imageView;
    Button button;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
