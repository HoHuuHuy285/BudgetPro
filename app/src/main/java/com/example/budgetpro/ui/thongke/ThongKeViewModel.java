package com.example.budgetpro.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetpro.entity.ThongKeLoaiChi;
import com.example.budgetpro.entity.ThongKeLoaiThu;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.repository.ChiRepository;
import com.example.budgetpro.repository.ThuRepository;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuRepository mThuresponsitory;

    private ChiRepository mChiresponsitory;
    private LiveData<Float> mTongThu;

    private LiveData<Float> mTongChi;

    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;

    private LiveData<List<ThongKeLoaiChi>> mThongKeLoaiChis;



    public ThongKeViewModel(@NonNull Application application) {
        super(application);

        mThuresponsitory = new ThuRepository(application);
        mTongThu = mThuresponsitory.sumTongThu();
        mThongKeLoaiThus = mThuresponsitory.sumByLoaiThu();
        mChiresponsitory = new ChiRepository(application);
        mTongChi = mChiresponsitory.sumTongChi();
        mThongKeLoaiChis = mChiresponsitory.sumByLoaiChi();
    }
    public LiveData<Float> getTongThu(){
        return mTongThu;
    }
    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus(){
        return mThongKeLoaiThus;
    }

    public LiveData<Float> getTongChi(){
        return mTongChi;
    }
    public LiveData<List<ThongKeLoaiChi>> getThongKeLoaiChis(){
        return mThongKeLoaiChis;
    }
}
