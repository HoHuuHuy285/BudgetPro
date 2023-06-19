package com.example.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class LoaiChi {
    @PrimaryKey(autoGenerate = true)
    public int lcid;
    @ColumnInfo(name = "ten")
    public String ten;
}
