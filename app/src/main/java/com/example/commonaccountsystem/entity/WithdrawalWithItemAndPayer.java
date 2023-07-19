package com.example.commonaccountsystem.entity;

import androidx.room.Embedded;

import java.io.Serializable;

public class WithdrawalWithItemAndPayer implements Serializable {
    @Embedded
    public Withdrawal withdrawal;

    public String itemName;

    public String payerName;
}
