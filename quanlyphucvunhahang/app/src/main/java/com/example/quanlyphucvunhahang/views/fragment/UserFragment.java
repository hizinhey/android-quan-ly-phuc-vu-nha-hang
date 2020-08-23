package com.example.quanlyphucvunhahang.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;

public class UserFragment extends Fragment implements LifecycleOwner {
    private HomeViewModel mViewModel;
    private TextView txtEmail; // User
    private TextView txtName;
    private TextView txtPhone;
    private TextView txtAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        mViewModel.getmTaiKhoan().observe(this, new Observer<TaiKhoanEntity>() {
            @Override
            public void onChanged(@Nullable TaiKhoanEntity user) {
                if(user == null){
                    user = new TaiKhoanEntity();
                }
                txtName.setText("Tên: " + user.getTen());
                txtPhone.setText("Số điện thoại: " + user.getSdt());
                txtEmail.setText("Email: " + user.getEmail());
                txtAddress.setText("Địa chỉ: " + user.getDiachi());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtEmail = view.findViewById(R.id.txtInfoEmail);
        txtName = view.findViewById(R.id.txtInfoName);
        txtPhone = view.findViewById(R.id.txtInfoPhone);
        txtAddress = view.findViewById(R.id.txtInfoAddress);
    }
}
