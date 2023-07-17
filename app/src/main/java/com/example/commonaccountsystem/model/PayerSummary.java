package com.example.commonaccountsystem.model;

import com.example.commonaccountsystem.entity.Payer;
import com.example.commonaccountsystem.entity.Withdrawal;

import java.util.List;

public class PayerSummary {
    private Payer payer;
    private int amount;
    private int difference;

    public PayerSummary(Payer payer, List<Withdrawal> personalWithdrawals){
        this.payer = payer;
        calcPayerAmount(personalWithdrawals);
    }

    private void calcPayerAmount(List<Withdrawal> withdrawals){
        this.amount = withdrawals.stream().mapToInt(w -> w.price).sum();
    }

    public void calcDifference(int avgAmount){
        this.difference = amount - avgAmount;
    }

    public String getPayerName(){
        if(payer == null){
            return null;
        }
        return this.payer.name;
    }

    public int getAmount(){
        return this.amount;
    }

    public int getDifference(){
        return this.difference;
    }
}
