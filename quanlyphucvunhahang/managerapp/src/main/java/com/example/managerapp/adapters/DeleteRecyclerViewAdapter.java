package com.example.managerapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.GlideApp;
import com.example.managerapp.R;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class DeleteRecyclerViewAdapter extends RecyclerView.Adapter<DeleteRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<MonAnEntity> list;

    public DeleteRecyclerViewAdapter(Context mContext, List<MonAnEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.delete_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MonAnEntity item = list.get(position);
        holder.textView.setText(item.getTenMonAn());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(item.getLinkHinhAnh());

        GlideApp.with(mContext)
                .load(storageReference)
                .into(holder.imageView);

        //TODO: thêm sự kiện xóa món ăn
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;
        public Button button;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_delete_tenmonan);
            imageView = view.findViewById(R.id.item_delete_img);
            button = view.findViewById(R.id.item_delete_button);
        }
    }
}
