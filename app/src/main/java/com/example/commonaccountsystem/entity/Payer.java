package com.example.commonaccountsystem.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "payer", indices = {@Index(value = "name", unique = true)})
public class Payer {
    @PrimaryKey
    public int id;

    @NonNull
    public String name;
}
