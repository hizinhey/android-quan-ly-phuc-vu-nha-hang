package com.example.managerapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.R;
import com.example.managerapp.models.modelsDAO.BuaAnDAO;
import com.example.managerapp.models.modelsDAO.LichSuDAO;
import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.example.managerapp.models.modelsEntity.ChiTietBuaAn;
import com.example.managerapp.models.modelsEntity.ChiTietLichSu;
import com.example.managerapp.models.modelsEntity.LichSuEntity;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.example.managerapp.views.LoginActivity;
import com.example.managerapp.views.QuanLyBuaAnActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;
import java.util.List;

public class BuaAnRecyclerViewAdapter extends RecyclerView.Adapter<BuaAnRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<BuaAnEntity> list;

    public BuaAnRecyclerViewAdapter(Context mContext, List<BuaAnEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView ten;
        TextView ban;
        TextView somonan;
        TextView trangthai;
        Button chitiet;
        Button hoantat;

        public ViewHolder(View view) {
            super(view);
            ten = view.findViewById(R.id.lbl_ten_quanly);
            ban = view.findViewById(R.id.lbl_banAn_quanly);
            somonan = view.findViewById(R.id.lbl_soMon_quanly);
            trangthai = view.findViewById(R.id.lbl_trangthai_quanly);
            chitiet = view.findViewById(R.id.btn_chitiet_quanly);
            hoantat = view.findViewById(R.id.btn_xacnhan_quanly);
        }
    }

    @NonNull
    @Override
    public BuaAnRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.quanly_buaan_item, parent, false);
        BuaAnRecyclerViewAdapter.ViewHolder viewHolder = new BuaAnRecyclerViewAdapter.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BuaAnEntity entity = list.get(position);
        holder.ten.setText(entity.getKhachHang().getTen());
        holder.ban.setText("Bàn ăn: " + entity.getBanAn());
        holder.trangthai.setText("Trạng thái: " + entity.strTrangThai());
        int i = 0;
        for(ChiTietBuaAn element: entity.getListChiTietBuaAn()){
            if(element.getTrangThai() == 0)
                i++;
        }
        holder.somonan.setText("Số món còn lại: " + i);

        //TODO: Xử lí 2 button
        if(entity.getTrangThai() == 0)
            holder.hoantat.setEnabled(false);
        holder.hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(mContext);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                final LichSuDAO dao = new LichSuDAO();
                dao.get(entity.getID()).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                LichSuEntity entityLichSu = document.toObject(LichSuEntity.class);
                                List<ChiTietLichSu> list = entityLichSu.getChiTiet();
                                list.add(new ChiTietLichSu(entity, new Date()));
                                entityLichSu.setChiTiet(list);
                                dao.change(entityLichSu).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        BuaAnDAO buaAnDAO = new BuaAnDAO();
                                        buaAnDAO.delete(entity.getID());
                                        pd.dismiss();
                                    }
                                });
                            } else {
                                pd.dismiss();
                            }
                        } else {
                            pd.dismiss();
                        }
                    }
                });
            }
        });

        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuanLyBuaAnActivity.class);
                intent.putExtra("BuaAn", entity.getID());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
