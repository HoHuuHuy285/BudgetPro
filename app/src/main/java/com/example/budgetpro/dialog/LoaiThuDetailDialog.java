package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.ui.thu.LoaiThuFragment;
import com.example.budgetpro.ui.thu.LoaiThuViewModel;

public class LoaiThuDetailDialog {
    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId, tvName;

    public LoaiThuDetailDialog(final Context context, LoaiThuFragment fragment, LoaiThu  loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_thu, null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvNameChi);
        tvId.setText(""+loaiThu.lid);
        tvName.setText(loaiThu.ten);
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
