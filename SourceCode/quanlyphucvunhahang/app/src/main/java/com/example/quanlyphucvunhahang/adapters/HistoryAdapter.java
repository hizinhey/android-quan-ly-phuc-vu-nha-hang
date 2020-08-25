package com.example.quanlyphucvunhahang.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietBuaAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietLichSu;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<ChiTietLichSu> list;

    public HistoryAdapter(Context mContext, List<ChiTietLichSu> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.lichsu_item, parent, false);
        HistoryAdapter.ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChiTietLichSu chiTietLichSu = list.get(position);
        holder.Khach.setText(chiTietLichSu.getBuaAn().getKhachHang().getTen());
        holder.BanAn.setText("Bàn ăn: " + chiTietLichSu.getBuaAn().getBanAn());

        Date date = chiTietLichSu.getTime();

        holder.ThoiGian.setText("Thời gian: " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date));

        float a = 0;
        for(ChiTietBuaAn element: chiTietLichSu.getBuaAn().getListChiTietBuaAn()){
            a += element.getMonAn().getGia();
        }
        holder.Gia.setText(a + "$");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView Khach;
        public TextView BanAn;
        public TextView ThoiGian;
        public TextView Gia;
        public ViewHolder(View view) {
            super(view);
            Khach = view.findViewById(R.id.lichsu_tenKhachHang);
            BanAn = view.findViewById(R.id.lichsu_banan);
            Gia = view.findViewById(R.id.lichsu_gia);
            ThoiGian = view.findViewById(R.id.lichsu_thoigian);
        }
    }


}
