package com.example.quanlyphucvunhahang.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quanlyphucvunhahang.models.modelsDAO.TaiKhoanDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<TaiKhoanEntity> user = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<TaiKhoanEntity> getUser() {
        return user;
    }

    public void setUser(TaiKhoanEntity user) {
        this.user.setValue(user);
    }
}
