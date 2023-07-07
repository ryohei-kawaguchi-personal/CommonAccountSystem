package com.example.commonaccountsystem.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "withdrawal",
        foreignKeys = {@ForeignKey(
                entity = Item.class,
                parentColumns = "id",
                childColumns = "item_id"),
                @ForeignKey(
                entity = Payer.class,
                parentColumns = "id",
                childColumns = "payer_id")
        }
)
public class Withdrawal{
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "item_id", index = true)
    public int itemId;

    @NonNull
    @ColumnInfo(name = "payer_id", index = true)
    public int payerId;

    @NonNull
    public int price;

    @NonNull
    public String liquidation_date;

    public String comment;


}
