package com.example.managerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.example.managerapp.models.modelsEntity.TaiKhoanEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel{
    private MutableLiveData<TaiKhoanEntity> mTaiKhoan = new MutableLiveData<>();
    private MutableLiveData<List<MonAnEntity>> mListMonAn = new MutableLiveData<>();
    private MutableLiveData<List<BuaAnEntity>> mListBuaAn = new MutableLiveData<>();

    private MutableLiveData<Boolean> isReady = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        isReady.setValue(false);

        mListMonAn.setValue(new ArrayList<MonAnEntity>());
        mTaiKhoan.setValue(new TaiKhoanEntity());
        mListBuaAn.setValue(new ArrayList<BuaAnEntity>());
    }

    public MutableLiveData<TaiKhoanEntity> getmTaiKhoan() {
        return mTaiKhoan;
    }

    public void setmTaiKhoan(TaiKhoanEntity mTaiKhoan) {
        this.mTaiKhoan.setValue(mTaiKhoan);
    }

    public MutableLiveData<Boolean> getIsReady() {
        return isReady;
    }

    public void setIsReady(Boolean isReady) {
        this.isReady.setValue(isReady);
    }

    public MutableLiveData<List<MonAnEntity>> getmListMonAn() {
        return mListMonAn;
    }

    public void setmListMonAn(List<MonAnEntity> mListMonAn) {
        this.mListMonAn.setValue(mListMonAn);
    }

    public MutableLiveData<List<BuaAnEntity>> getmListBuaAn() {
        return mListBuaAn;
    }

    public void setmListBuaAn(List<BuaAnEntity> mListBuaAn) {
        this.mListBuaAn.setValue(mListBuaAn);
    }
}
