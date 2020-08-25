package com.example.managerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerapp.GlideApp;
import com.example.managerapp.R;
import com.example.managerapp.models.modelsDAO.BuaAnDAO;
import com.example.managerapp.models.modelsDAO.LichSuDAO;
import com.example.managerapp.models.modelsEntity.BuaAnEntity;
import com.example.managerapp.models.modelsEntity.ChiTietBuaAn;
import com.example.managerapp.models.modelsEntity.ChiTietLichSu;
import com.example.managerapp.models.modelsEntity.LichSuEntity;
import com.example.managerapp.models.modelsEntity.MonAnEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;
import java.util.List;

public class MonAnRecyclerViewAdapter extends RecyclerView.Adapter<MonAnRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<ChiTietBuaAn> list;
    private String buaAn;

    public MonAnRecyclerViewAdapter(Context mContext, List<ChiTietBuaAn> list, String buaAn) {
        this.mContext = mContext;
        this.list = list;
        this.buaAn = buaAn;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView ten;
        ImageView imageView;
        TextView trangthai;
        Button hoantat;

        public ViewHolder(View view) {
            super(view);
            ten = view.findViewById(R.id.lbl_ten_monan);
            trangthai = view.findViewById(R.id.lbl_trangthai_monan);
            imageView = view.findViewById(R.id.img_anh_monan);
            hoantat = view.findViewById(R.id.btn_hoantat_monan);
        }
    }

    @NonNull
    @Override
    public MonAnRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.quanly_monan_item, parent, false);
        MonAnRecyclerViewAdapter.ViewHolder viewHolder = new MonAnRecyclerViewAdapter.ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnRecyclerViewAdapter.ViewHolder holder, final int position) {
        final ChiTietBuaAn entity = list.get(position);
        holder.ten.setText(entity.getMonAn().getTenMonAn());
        holder.trangthai.setText("Trạng thái: " + entity.strTrangThai());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(entity.getMonAn().getLinkHinhAnh());

        GlideApp.with(mContext)
                .load(storageReference)
                .into(holder.imageView);

        if (entity.getTrangThai() == 1)
            holder.hoantat.setEnabled(false);
        entity.setTrangThai(1);
        holder.hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BuaAnDAO dao = new BuaAnDAO();
                dao.get(buaAn).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                BuaAnEntity buaAnEntity = document.toObject(BuaAnEntity.class);
                                buaAnEntity.getListChiTietBuaAn().set(position, entity);
                                dao.set(buaAn, buaAnEntity);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
