package com.example.quanlyphucvunhahang.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsDAO.BuaAnDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.LichSuDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.MonAnDAO;
import com.example.quanlyphucvunhahang.models.modelsDAO.TaiKhoanDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.BuaAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.LichSuEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;
import com.example.quanlyphucvunhahang.views.fragment.HistoryFragment;
import com.example.quanlyphucvunhahang.views.fragment.HomeFragment;
import com.example.quanlyphucvunhahang.views.fragment.MealFragment;
import com.example.quanlyphucvunhahang.views.fragment.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActionBar toolbar;
    UserFragment userFragment;
    HistoryFragment historyFragment;
    HomeFragment homeFragment;
    MealFragment mealFragment;

    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ViewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadData();

        toolbar.setTitle("Nhà hàng");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.page_1:
                    homeFragment = new HomeFragment();
                    toolbar.setTitle("Nhà hàng");
                    loadFragment(homeFragment);
                    return true;
                case R.id.page_2:
                    mealFragment = new MealFragment();
                    toolbar.setTitle("Bữa ăn");
                    loadFragment(mealFragment);
                    return true;
                case R.id.page_3:
                    historyFragment = new HistoryFragment();
                    loadFragment(historyFragment);
                    toolbar.setTitle("Lịch sử");
                    return true;
                case R.id.page_4:
                    userFragment = new UserFragment();
                    loadFragment(userFragment);
                    toolbar.setTitle("Thông tin cá nhân");
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadData() {
        Intent getIntent = getIntent();
        String id = getIntent.getStringExtra("USER");

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        taiKhoanDAO.get(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot o) {
                TaiKhoanEntity user = o.toObject(TaiKhoanEntity.class);
                homeViewModel.getmTaiKhoan().setValue(user);
                homeFragment = new HomeFragment();
                loadFragment(homeFragment);
            }
        });

        MonAnDAO dao = new MonAnDAO();
        dao.getAll().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<MonAnEntity> khaivi = new ArrayList<>();
                    List<MonAnEntity> monchinh = new ArrayList<>();
                    List<MonAnEntity> trangmieng = new ArrayList<>();
                    List<MonAnEntity> monnuoc = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MonAnEntity monAnEntity = document.toObject(MonAnEntity.class);

                        if (monAnEntity.getNhomMonAn().equals(MonAnEntity.KHAI_VI)) {
                            khaivi.add(monAnEntity);
                        }
                        if (monAnEntity.getNhomMonAn().equals(MonAnEntity.MON_CHINH)) {
                            monchinh.add(monAnEntity);
                        }
                        if (monAnEntity.getNhomMonAn().equals(MonAnEntity.TRANG_MIENG)) {
                            trangmieng.add(monAnEntity);
                        }
                        if (monAnEntity.getNhomMonAn().equals(MonAnEntity.MON_NUOC)) {
                            monnuoc.add(monAnEntity);
                        }
                    }

                    homeViewModel.setmListMonAnKhaiVi(khaivi);
                    homeViewModel.setmListMonAnMonChinh(monchinh);
                    homeViewModel.setmListMonAnTrangMieng(trangmieng);
                    homeViewModel.setmListMonAnMonNuoc(monnuoc);
                }
            }
        });

        BuaAnDAO buaAnDAO = new BuaAnDAO();
        buaAnDAO.get(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                  @Override
                                                  public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                      BuaAnEntity buaAnEntity = documentSnapshot.toObject(BuaAnEntity.class);
                                                      homeViewModel.setmBuaAn(buaAnEntity);
                                                  }
                                              }
        );

        LichSuDAO lichSuDAO = new LichSuDAO();
        lichSuDAO.get(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot o) {
                LichSuEntity lichSuEntity = o.toObject(LichSuEntity.class);
                homeViewModel.getmLichSu().setValue(lichSuEntity);
            }
        });
    }
}
