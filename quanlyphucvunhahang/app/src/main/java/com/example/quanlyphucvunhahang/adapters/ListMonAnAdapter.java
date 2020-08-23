package com.example.quanlyphucvunhahang.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlyphucvunhahang.R;
import com.example.quanlyphucvunhahang.models.modelAdapter.MonAnItemAdapter;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;

import java.util.ArrayList;
import java.util.List;

public class ListMonAnAdapter extends RecyclerView.Adapter<ListMonAnAdapter.ViewHolder> {
    private Context mContext;
    private List<MonAnItemAdapter> list;

    public ListMonAnAdapter(Context mContext, List<MonAnItemAdapter> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textview_item);
            imageView = view.findViewById(R.id.cardview_item);
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
        MonAnItemAdapter item = list.get(position);
        //TODO: xử lí dữ liệu gửi từ mạng về
        holder.textView.setText("Cơm âm phủ");
        holder.imageView.setImageResource(R.drawable.icons8_home_48_filled);
//        Glide.with(mContext)
//                .load(item.getLinkImage())
//                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
