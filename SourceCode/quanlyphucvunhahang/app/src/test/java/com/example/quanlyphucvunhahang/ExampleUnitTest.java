package com.example.quanlyphucvunhahang;

import android.app.Activity;

import com.example.quanlyphucvunhahang.models.modelsDAO.KhuyenMaiDAO;
import com.example.quanlyphucvunhahang.helpers.ExampleEntity;
import com.google.firebase.FirebaseApp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addSuccess(){
        FirebaseApp.initializeApp(new Activity().getBaseContext());
        assertTrue(new KhuyenMaiDAO().add(ExampleEntity.createExampleKhuyenMai(null)));
    }
}