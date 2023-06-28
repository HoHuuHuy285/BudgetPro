package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.LoaiChiSpinnerAdapter;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.ui.chi.KhoanChiFragment;
import com.example.budgetpro.ui.chi.KhoanChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ChiDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName, etAmount, etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiChiSpinnerAdapter mAdapter;
    public ChiDialog(final Context context, KhoanChiFragment fragment, Chi ... chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getmAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
        spType.setAdapter(mAdapter);
        if ( chi != null && chi.length > 0  ){
            etId.setText(""+chi[0].cid);
            etName.setText(chi[0].ten);
            etAmount.setText(""+chi[0].sotien);
            etNote.setText(chi[0].ghichu);
            mEditMode = true;
        } else {
            mEditMode = false;
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
                        Chi lc = new Chi();
                        lc.ten = etName.getText().toString();
                        lc.sotien = Float.parseFloat(etAmount.getText().toString());
                        lc.ghichu = etNote.getText().toString();
                        lc.lcid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).llid;
                        if ( mEditMode) {
                            lc.lcid = Integer.parseInt(etId.getText().toString());
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
