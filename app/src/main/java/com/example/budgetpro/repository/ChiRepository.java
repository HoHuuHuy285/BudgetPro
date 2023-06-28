package com.example.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetpro.dao.AppDatabase;
import com.example.budgetpro.dao.ChiDao;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.entity.ThongKeLoaiChi;
import com.example.budgetpro.entity.ThongKeLoaiThu;

import java.util.List;

public class ChiRepository {

    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public ChiRepository(Application application) {
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi = mChiDao.findAll();

    }

    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }

    public LiveData<Float> sumTongChi() {
        return mChiDao.sumTongChi();
    }

    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi() {
        return mChiDao.sumByLoaiChi();
    }


    public void insert(Chi chi) {
        new InsertAsyncTask(mChiDao).execute(chi);
    }
    public void delete(Chi chi) {
        new DeleteAsyncTask(mChiDao).execute(chi);
    }

    public void update(Chi chi){
        new UpdateAsyncTask(mChiDao).execute(chi);
    }
    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }

        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.update(chis[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public InsertAsyncTask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }

        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.insert(chis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public DeleteAsyncTask(ChiDao chiDao) {
            this.mChiDao = chiDao;
        }

        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.delete(chis[0]);
            return null;
        }
    }
}


