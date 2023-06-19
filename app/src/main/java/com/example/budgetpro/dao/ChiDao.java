package com.example.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.entity.ThongKeLoaiChi;
import com.example.budgetpro.entity.ThongKeLoaiThu;
import com.example.budgetpro.entity.Thu;

import java.util.List;

@Dao
public interface ChiDao {
    @Query("SELECT * FROM chi")
    LiveData<List<Chi>> findAll();

    @Query("SELECT sum(sotien) FROM chi")
    LiveData<Float> sumTongThu();

    @Query("SELECT b.lcid, b.ten,  sum(a.sotien) as tong FROM thu a INNER JOIN loaichi b on a.ltid = b.lcid " + "GROUP BY b.lcid, b.ten")
    LiveData<List<ThongKeLoaiChi>> sumByLoaiChi();


    @Insert
    void insert(Chi chi);

    @Update
    void update(Chi chi);

    @Delete
    void delete(Chi chi);
}



