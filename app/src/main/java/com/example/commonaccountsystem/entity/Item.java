package com.example.commonaccountsystem.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "item", indices = {@Index(value = "name", unique = true)})
public class Item implements Serializable{
    @PrimaryKey
    public int id;

    @NonNull
    public String name;

    @NonNull
    public int cost;

    @ColumnInfo(name = "payment_date")
    public String paymentDate;

    @ColumnInfo(name = "display_order")
    public int displayOrder;
}
