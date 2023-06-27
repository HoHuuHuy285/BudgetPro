package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.ui.chi.LoaiChiFragment;
import com.example.budgetpro.ui.chi.LoaiChiViewModel;
import com.example.budgetpro.ui.chi.LoaiChiFragment;
import com.example.budgetpro.ui.chi.LoaiChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName;
    private boolean mEditMode1;

    public LoaiChiDialog(final Context context, LoaiChiFragment fragment, LoaiChi ... loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_chi, null);
        etId = view.findViewById(R.id.etidChi);
        etName = view.findViewById(R.id.etNameChi);
        if ( loaiChi != null && loaiChi.length > 0  ){
            etId.setText(""+loaiChi[0].llid);
            etName.setText(loaiChi[0].ten);
            mEditMode1 = true;
        } else {
            mEditMode1 = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoaiChi lc = new LoaiChi();
                        lc.ten = etName.getText().toString();
                        if ( mEditMode1) {
                            lc.llid = Integer.parseInt(etId.getText().toString());
                            mViewModel.update(lc);
                        } else {
                            mViewModel.insert(lc);
                            Toast.makeText(context, "Loại Chi Được Lưu", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        mDialog = builder.create();

    }
    public void show(){
        mDialog.show();
    }
}
