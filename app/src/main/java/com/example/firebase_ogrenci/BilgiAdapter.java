package com.example.firebase_ogrenci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BilgiAdapter extends RecyclerView.Adapter<BilgiAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView projeAdi, projeAlani, sonTeslimTarihi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projeAdi = itemView.findViewById(R.id.list_item_adi);
            projeAlani = itemView.findViewById(R.id.list_item_fiyat);
            sonTeslimTarihi = itemView.findViewById(R.id.list_item_adet);
        }
    }

    Context context;
    ArrayList<Bilgi> arrayList;

    public BilgiAdapter(Context context, ArrayList<Bilgi> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BilgiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.proje_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BilgiAdapter.ViewHolder holder, int position) {
        holder.projeAdi.setText("Proje Adı: " + arrayList.get(position).getProjeAdi());
        holder.projeAlani.setText("Proje Alanı: " + arrayList.get(position).getProjeAlani());
        holder.sonTeslimTarihi.setText("Son Teslim Tarihi: " + arrayList.get(position).getSonTeslimTarihi());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
