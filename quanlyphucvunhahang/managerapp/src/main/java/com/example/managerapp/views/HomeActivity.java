package com.example.managerapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.managerapp.R;
import com.example.managerapp.models.modelsDAO.BuaAnDAO;
import com.example.managerapp.models.modelsDAO.MonAnDAO;
import com.example.managerapp.models.modelsDAO.TaiKhoanDAO;
import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.example.managerapp.models.modelsEntity.TaiKhoanEntity;
import com.example.managerapp.viewmodels.HomeViewModel;
import com.example.managerapp.views.fragment.DeleteFragment;
import com.example.managerapp.views.fragment.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActionBar toolbar;

    // Fragment
    DeleteFragment deleteFragment;
    UserFragment userFragment;

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
                    //homeFragment = new HomeFragment();
                    toolbar.setTitle("Quản lý");
                    //loadFragment(homeFragment);
                    return true;
                case R.id.page_2:
                    //mealFragment = new MealFragment();
                    toolbar.setTitle("Thêm món ăn");
                    //loadFragment(mealFragment);
                    return true;
                case R.id.page_3:
                    deleteFragment = new DeleteFragment();
                    loadFragment(deleteFragment);
                    toolbar.setTitle("Xóa món ăn");
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

        // Tài khoản
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        taiKhoanDAO.get(id).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot o) {
                TaiKhoanEntity user = o.toObject(TaiKhoanEntity.class);
                homeViewModel.getmTaiKhoan().setValue(user);
            }
        });

        MonAnDAO dao = new MonAnDAO();
        dao.getAll().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                               @Override
                                               public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                   if (task.isSuccessful()) {
                                                       List<MonAnEntity> list = new ArrayList<>();

                                                       for (QueryDocumentSnapshot document : task.getResult()) {
                                                           MonAnEntity monAnEntity = document.toObject(MonAnEntity.class);
                                                           list.add(monAnEntity);
                                                       }

                                                       homeViewModel.setmListMonAn(list);
                                                   }

                                               }
                                           }
        );
    }
}

