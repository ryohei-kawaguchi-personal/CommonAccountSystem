package com.example.commonaccountsystem.model;

import com.example.commonaccountsystem.entity.Item;
import com.example.commonaccountsystem.entity.Withdrawal;

import java.util.List;

public class ItemSummary {
    private Item item;
    private int amount;

    public ItemSummary(Item item, List<Withdrawal> itemWithdrawals){
        this.item = item;
        calcItemAmount(itemWithdrawals);
    }

    private void calcItemAmount(List<Withdrawal> withdrawals){
        this.amount = withdrawals.stream().mapToInt(w -> w.price).sum();
    }

    public String getItemName(){
        if(item == null){
            return null;
        }
        return this.item.name;
    }

    public int getAmount(){
        return this.amount;
    }
}
