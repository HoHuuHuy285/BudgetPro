package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.ThongKeLoaiChi;

import java.util.List;

public class ThongKeLoaiChiRecyclerViewAdapter
        extends RecyclerView.Adapter<ThongKeLoaiChiRecyclerViewAdapter.ThongKeLoaiChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List <ThongKeLoaiChi> mListChi;


    public ThongKeLoaiChiRecyclerViewAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public ThongKeLoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_thongke_loaichi, parent, false);


        return new ThongKeLoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiChiViewHolder holder, int position) {

        if(mListChi != null){
            holder.tvTenLoaiChi.setText(mListChi.get(position).ten);
            holder.etTongLoaiChi.setText("" + mListChi.get(position).tong);
        }

    }

    @Override
    public int getItemCount() {
        if(mListChi == null)
            return 0;
        return mListChi.size();
    }

    public void setList(List <ThongKeLoaiChi> list){
        this.mListChi = list;
        notifyDataSetChanged();
    }

    public static class ThongKeLoaiChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTenLoaiChi;
        public EditText etTongLoaiChi;

        public ThongKeLoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiChi = itemView.findViewById(R.id.tvTenLoaiChi);
            etTongLoaiChi = itemView.findViewById(R.id.etTongLoaiChi);
        }
    }
}
