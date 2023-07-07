package com.example.commonaccountsystem.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "item", indices = {@Index(value = "name", unique = true)})
public class Item {
    @PrimaryKey
    public int id;

    @NonNull
    public String name;

    @NonNull
    public int cost;
}
