package com.example.commonaccountsystem.entity;

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
public class Withdrawal implements Serializable {
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
    @ColumnInfo(name = "liquidation_date", index = true)
    public String liquidationDate;

    public String comment;


}
