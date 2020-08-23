package com.example.quanlyphucvunhahang.helpers;

import com.example.quanlyphucvunhahang.models.modelsEntity.BuaAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietBuaAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.NhomMonAnEntity;

import java.util.ArrayList;
import java.util.Date;

public class ExampleEntity {
    public static KhuyenMaiEntity createExampleKhuyenMai(String id){
        return new KhuyenMaiEntity(
                id,
                "Mọi nhà đều vui",
                "Chương trình khuyến mãi giảm giá 50% tất cả các món ăn trên thực đơn.",
                new Date(),
                new Date(),
                "*0.5"
        );
    }
    public static BuaAnEntity createExampleBuaAn(String id){
        return new BuaAnEntity();
    }
    public static MonAnEntity createExampleMonAn(String id){
        return new MonAnEntity(
                id,
                Float.parseFloat("500000"),
                "Sườn xào chua ngọt",
                "Sườn rưới sốt me đậm vị hơn cho bữa ăn ngon",
                ExampleEntity.createExampleKhuyenMai("KM0000000001"),
                new NhomMonAnEntity("NA0000000001", "Bữa chính"),
                "/abc.jpg"
        );
    }
}
