package com.example.quanlyphucvunhahang.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;
import com.example.quanlyphucvunhahang.views.ChiTietMonAnActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ListMonAnAdapter extends RecyclerView.Adapter<ListMonAnAdapter.ViewHolder> {
    private Context mContext;
    private List<MonAnEntity> list;
    private String taiKhoan;

    public ListMonAnAdapter(Context mContext, List<MonAnEntity> list, String taiKhoan) {
        this.mContext = mContext;
        this.list = list;
        this.taiKhoan = taiKhoan;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;
        public CardView cardView;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textview_item);
            imageView = view.findViewById(R.id.cardview_item);
            cardView = view.findViewById(R.id.cardview_monan);
        }
    }

    @NonNull
    @Override
    public ListMonAnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.cardview_monan, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListMonAnAdapter.ViewHolder holder, int position) {
        final MonAnEntity item = list.get(position);
        //TODO: xử lí dữ liệu gửi từ mạng về
        holder.textView.setText(item.getTenMonAn());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(item.getLinkHinhAnh());

        GlideApp.with(mContext)
                .load(storageReference)
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChiTietMonAnActivity.class);
                intent.putExtra("MonAn", item.getID());
                intent.putExtra("TaiKhoan", taiKhoan);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
