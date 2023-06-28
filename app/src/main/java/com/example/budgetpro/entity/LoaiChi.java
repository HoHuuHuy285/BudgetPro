package com.example.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiChi {
    @PrimaryKey(autoGenerate = true)
    public int llid;
    @ColumnInfo(name = "ten")
    public String ten;
}
