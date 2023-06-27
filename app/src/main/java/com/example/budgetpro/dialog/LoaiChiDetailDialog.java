package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.ui.chi.LoaiChiFragment;
import com.example.budgetpro.ui.chi.LoaiChiViewModel;

public class LoaiChiDetailDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId, tvName;

    public LoaiChiDetailDialog(final Context context, LoaiChiFragment fragment, LoaiChi  loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_chi, null);
        tvId = view.findViewById(R.id.tvIdChi);
        tvName = view.findViewById(R.id.tvnameChi);
        tvId.setText(""+loaiChi.llid);
        tvName.setText(loaiChi.ten);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create();

    }
    public void show(){
        mDialog.show();
    }
}
