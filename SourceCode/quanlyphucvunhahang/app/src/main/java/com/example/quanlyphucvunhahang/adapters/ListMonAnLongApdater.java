package com.example.quanlyphucvunhahang.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyphucvunhahang.GlideApp;
import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietBuaAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.views.ChiTietMonAnActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ListMonAnLongApdater extends RecyclerView.Adapter<ListMonAnLongApdater.ViewHolder>{
    private Context mContext;
    private List<ChiTietBuaAn> list;

    public ListMonAnLongApdater(Context mContext, List<ChiTietBuaAn> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.cardview_monan_long, parent, false);
        ListMonAnLongApdater.ViewHolder viewHolder = new ListMonAnLongApdater.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAnEntity item = list.get(position).getMonAn();
        holder.ten.setText(item.getTenMonAn());
        holder.gia.setText(item.getGia() + " $");
        holder.status.setText(list.get(position).strTrangThai());
        holder.status.setTextColor(Color.parseColor(list.get(position).colorTrangThai()));
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(item.getLinkHinhAnh());
        GlideApp.with(mContext)
                .load(storageReference)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView ten;
        public ImageView image;
        public TextView gia;
        public TextView status;
        public ViewHolder(View view) {
            super(view);
            ten = view.findViewById(R.id.lblCVLMonAnTitle);
            image = view.findViewById(R.id.imgCVLMonAn);
            gia = view.findViewById(R.id.lblCVLMonAnGia);
            status = view.findViewById(R.id.lblCVLMonAnTrangThai);
        }
    }
}
