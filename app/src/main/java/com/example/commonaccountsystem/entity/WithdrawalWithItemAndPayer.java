package com.example.commonaccountsystem.entity;

import androidx.room.Embedded;

public class WithdrawalWithItemAndPayer {
    @Embedded
    public Withdrawal withdrawal;

    public String itemName;

    public String payerName;
}
