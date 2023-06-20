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


    public void insert(Chi Chi) {
        new InsertAsyncTask(mChiDao).execute(Chi);
    }
    public void delete(Chi Chi) {
        new DeleteAsyncTask(mChiDao).execute(Chi);
    }

    public void update(Chi Chi){
        new UpdateAsyncTask(mChiDao).execute(Chi);
    }
    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.update(Chis[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public InsertAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.insert(Chis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;
        public DeleteAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;
        }

        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.delete(Chis[0]);
            return null;
        }
    }
}


