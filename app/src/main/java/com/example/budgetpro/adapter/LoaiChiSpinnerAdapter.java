package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;

import java.util.List;

public class LoaiChiSpinnerAdapter extends BaseAdapter {
    private List<LoaiChi> mList1;
    private LayoutInflater mLayoutInflater;

    public LoaiChiSpinnerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<LoaiChi> mList) {
        this.mList1 = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if ( mList1 == null)
            return 0;
        return mList1.size();
    }

    @Override
    public Object getItem(int i) {
        if( mList1 == null)
            return null;
        return mList1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KhoanChiViewHolder holder;
        if ( view == null ){
            view = mLayoutInflater.inflate(R.layout.spinner_chi_item, null , false);
            holder = new KhoanChiViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (KhoanChiViewHolder) view.getTag();
        }
        holder.tvNameChi.setText(mList1.get(i).ten);
        return view;
    }

    public static class KhoanChiViewHolder {
        public TextView tvNameChi;

        public KhoanChiViewHolder(View view) {tvNameChi = view.findViewById(R.id.tvnameChi);
        }
    }
}
