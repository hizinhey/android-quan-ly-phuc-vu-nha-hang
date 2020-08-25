package com.example.quanlyphucvunhahang.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.quanlyphucvunhahang.models.modelsEntity.BuaAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.LichSuEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel{
    private MutableLiveData<TaiKhoanEntity> mTaiKhoan = new MutableLiveData<>();
    private MutableLiveData<BuaAnEntity> mBuaAn = new MutableLiveData<>();
    private MutableLiveData<List<MonAnEntity>> mListMonAnKhaiVi = new MutableLiveData<>();
    private MutableLiveData<List<MonAnEntity>> mListMonAnMonChinh = new MutableLiveData<>();
    private MutableLiveData<List<MonAnEntity>> mListMonAnTrangMieng = new MutableLiveData<>();
    private MutableLiveData<List<MonAnEntity>> mListMonAnMonNuoc = new MutableLiveData<>();
    private MutableLiveData<List<KhuyenMaiEntity>> mKhuyenMai = new MutableLiveData<>();
    private MutableLiveData<LichSuEntity> mLichSu = new MutableLiveData<>();

    private MutableLiveData<Boolean> isReady = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        isReady.setValue(false);

        mTaiKhoan.setValue(new TaiKhoanEntity());
        mBuaAn.setValue(new BuaAnEntity());
        mListMonAnKhaiVi.setValue(new ArrayList<MonAnEntity>());
        mListMonAnMonChinh.setValue(new ArrayList<MonAnEntity>());
        mListMonAnMonNuoc.setValue(new ArrayList<MonAnEntity>());
        mListMonAnTrangMieng.setValue(new ArrayList<MonAnEntity>());
        mKhuyenMai.setValue(new ArrayList<KhuyenMaiEntity>());
        mLichSu.setValue(new LichSuEntity());
    }

    public MutableLiveData<TaiKhoanEntity> getmTaiKhoan() {
        return mTaiKhoan;
    }

    public void setmTaiKhoan(TaiKhoanEntity mTaiKhoan) {
        this.mTaiKhoan.setValue(mTaiKhoan);
    }

    public MutableLiveData<BuaAnEntity> getmBuaAn() {
        return mBuaAn;
    }

    public void setmBuaAn(BuaAnEntity mBuaAn) {
        this.mBuaAn.setValue(mBuaAn);
    }

    public MutableLiveData<List<MonAnEntity>> getmListMonAnKhaiVi() {
        return mListMonAnKhaiVi;
    }

    public void setmListMonAnKhaiVi(List<MonAnEntity> mListMonAnKhaiVi) {
        this.mListMonAnKhaiVi.setValue(mListMonAnKhaiVi);
    }

    public MutableLiveData<List<MonAnEntity>> getmListMonAnMonChinh() {
        return mListMonAnMonChinh;
    }

    public void setmListMonAnMonChinh(List<MonAnEntity> mListMonAnMonChinh) {
        this.mListMonAnMonChinh.setValue(mListMonAnMonChinh);
    }

    public MutableLiveData<List<MonAnEntity>> getmListMonAnTrangMieng() {
        return mListMonAnTrangMieng;
    }

    public void setmListMonAnTrangMieng(List<MonAnEntity> mListMonAnTrangMieng) {
        this.mListMonAnTrangMieng.setValue(mListMonAnTrangMieng);
    }

    public MutableLiveData<List<MonAnEntity>> getmListMonAnMonNuoc() {
        return mListMonAnMonNuoc;
    }

    public void setmListMonAnMonNuoc(List<MonAnEntity> mListMonAnMonNuoc) {
        this.mListMonAnMonNuoc.setValue(mListMonAnMonNuoc);
    }

    public MutableLiveData<LichSuEntity> getmLichSu() {
        return mLichSu;
    }

    public void setmLichSu(LichSuEntity mLichSu) {
        this.mLichSu.setValue(mLichSu);
    }

    public MutableLiveData<List<KhuyenMaiEntity>> getmKhuyenMai() {
        return mKhuyenMai;
    }

    public void setmKhuyenMai(List<KhuyenMaiEntity> mKhuyenMai) {
        this.mKhuyenMai.setValue(mKhuyenMai);
    }

    public MutableLiveData<Boolean> getIsReady() {
        return isReady;
    }

    public void setIsReady(Boolean isReady) {
        this.isReady.setValue(isReady);
    }

}
