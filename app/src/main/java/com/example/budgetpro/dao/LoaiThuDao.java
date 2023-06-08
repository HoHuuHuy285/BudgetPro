package com.example.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetpro.entity.LoaiThu;

import java.util.List;

@Dao
public interface LoaiThuDao {
    @Query("SELECT * FROM loaithu")
    LiveData<List<LoaiThu>> findAll();

    @Insert
    void insert(LoaiThu loaithu);

    @Update
    void update(LoaiThu loaiThu);

    @Delete
    void delete(LoaiThu loaiThu);
}


