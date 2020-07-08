package com.example.quanlyphucvunhahang.models.modelsDAO;

import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO {
    static public List<KhuyenMaiEntity> getAll(){
        List<KhuyenMaiEntity> result = new ArrayList<>();
        /* Lấy tất cả danh sách khuyến mãi */
        return result;
    }

    static public KhuyenMaiEntity get(String id){
        /* Lấy một khuyến mãi theo id */
        return null;
    }

    static public boolean add(KhuyenMaiEntity record){
        /* Thêm một khuyến mãi record */
        return false;
    }

    static public boolean change(KhuyenMaiEntity newRecord){
        /* Cập nhật khuyến mãi có id trùng với newRecord, bằng newRecord */
        return false;
    }

    static public boolean delete(String id){
        /* Xóa một khuyến mãi theo id */
        return false;
    }
}
