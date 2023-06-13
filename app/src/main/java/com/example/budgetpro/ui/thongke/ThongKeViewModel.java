package com.example.budgetpro.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.budgetpro.entity.ThongKeLoaiThu;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.repository.ThuRepository;

import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private ThuRepository mThuresponsitory;
    private LiveData<Float> mTongThu;

    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;



    public ThongKeViewModel(@NonNull Application application) {
        super(application);

        mThuresponsitory = new ThuRepository(application);
        mTongThu = mThuresponsitory.sumTongThu();
        mThongKeLoaiThus = mThuresponsitory.sumByLoaiThu();
    }
    public LiveData<Float> getTongThu(){
        return mTongThu;
    }
    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus(){
        return mThongKeLoaiThus;
    }
}
