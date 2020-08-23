package com.example.quanlyphucvunhahang.views;

import android.app.Activity;
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

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsDAO.TaiKhoanDAO;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;
import com.example.quanlyphucvunhahang.viewmodels.HomeViewModel;
import com.example.quanlyphucvunhahang.views.fragment.HistoryFragment;
import com.example.quanlyphucvunhahang.views.fragment.HomeFragment;
import com.example.quanlyphucvunhahang.views.fragment.MealFragment;
import com.example.quanlyphucvunhahang.views.fragment.UserFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;

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

        // Fragment
        userFragment = new UserFragment();
        historyFragment = new HistoryFragment();
        homeFragment = new HomeFragment();
        mealFragment = new MealFragment();

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Nhà hàng");
        loadFragment(homeFragment);
        loadData();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.page_1:
                    toolbar.setTitle("Nhà hàng");
                    loadFragment(homeFragment);
                    return true;
                case R.id.page_2:
                    toolbar.setTitle("Bữa ăn");
                    loadFragment(mealFragment);
                    return true;
                case R.id.page_3:
                    loadFragment(historyFragment);
                    toolbar.setTitle("Lịch sử");
                    return true;
                case R.id.page_4:
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

    private void loadData(){
        // User
        TaiKhoanEntity user = new TaiKhoanEntity();
        user.setID("TK000001");

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        taiKhoanDAO.get(user.getID()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot o) {
                TaiKhoanEntity user = o.toObject(TaiKhoanEntity.class);
                homeViewModel.getmTaiKhoan().setValue(user);
            }
        });


    }
}
