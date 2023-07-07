package com.example.commonaccountsystem.dto;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class WithdrawalWithItemAndPayer {
    @Embedded
    public Withdrawal withdrawal;

    public String itemName;

    public int cost;

    public String payerName;
}
