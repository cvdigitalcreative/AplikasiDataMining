package com.digitalcreative.aplikasidatamining.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalcreative.aplikasidatamining.Model.Model_LacakMobil;
import com.digitalcreative.aplikasidatamining.R;

import java.util.List;

public class Riwayat_RecyclerView extends RecyclerView.Adapter<Riwayat_RecyclerView.ViewHolder> {
    List<Model_LacakMobil> list;

    public Riwayat_RecyclerView(List<Model_LacakMobil> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Riwayat_RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Riwayat_RecyclerView.ViewHolder holder, int i) {
        Model_LacakMobil model = list.get(i);
        holder.nama_pemilik.setText(model.getNama());
        holder.no_plat.setText(model.getNo_plat());
        holder.mobil.setText(model.getNama_mobil());
        holder.cabang.setText(model.getCabang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView no_plat, nama_pemilik, mobil, cabang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_pemilik = itemView.findViewById(R.id.pemilik_mobil);
            no_plat = itemView.findViewById(R.id.no_pol);
            mobil = itemView.findViewById(R.id.nama_unit);
            cabang = itemView.findViewById(R.id.cabang);

        }
    }
}
