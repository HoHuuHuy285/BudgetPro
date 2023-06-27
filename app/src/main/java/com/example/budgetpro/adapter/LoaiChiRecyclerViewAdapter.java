package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;

import java.util.List;

public class LoaiChiRecyclerViewAdapter extends RecyclerView.Adapter<LoaiChiRecyclerViewAdapter.LoaiChiViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<LoaiChi> mListChi;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public LoaiChiRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiChiRecyclerViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiChiRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recycleview_loai_chi_item, parent, false);

        return new LoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        if (mListChi != null) {
            holder.tvNameChi.setText(mListChi.get(position).ten);
            holder.position = position;
        }

    }

    @Override
    public int getItemCount() {
        if ( mListChi == null)
            return 0;
        return mListChi.size();
    }
    public LoaiChi getItem(int position) {
        if (mListChi == null || position >= mListChi.size()) {
            return null;
        }
        return mListChi.get(position);
    }
    public void setList(List<LoaiChi> mListChi) {

        this.mListChi = mListChi;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameChi;
        public ImageView ivEditChi, ivViewChi;
        public CardView cvChi;
        public int position;
        public LoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameChi = itemView.findViewById(R.id.tvnameChi);
            ivViewChi = itemView.findViewById(R.id.ivViewChi);
            ivEditChi = itemView.findViewById(R.id.ivEditChi);
            cvChi = (CardView) itemView;
            ivViewChi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemViewClickListener != null) {
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });

            ivEditChi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ( itemEditClickListener != null) {
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}

