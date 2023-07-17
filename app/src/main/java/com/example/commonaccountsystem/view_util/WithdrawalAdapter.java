package com.example.commonaccountsystem.view_util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;

import java.util.List;

public class WithdrawalAdapter extends RecyclerView.Adapter<WithdrawalViewHolder>{
    private List<WithdrawalWithItemAndPayer> withdrawals;

    public WithdrawalAdapter(List<WithdrawalWithItemAndPayer> withdrawals) {
        this.withdrawals = withdrawals;
    }

    @Override
    public WithdrawalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.withdrawal, parent, false);
        return new WithdrawalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WithdrawalViewHolder holder, int position) {
        WithdrawalWithItemAndPayer withdrawal = this.withdrawals.get(position);
        String itemName = withdrawal.itemName;
        String payerName = withdrawal.payerName;
        int price = withdrawal.withdrawal.price;
        String liquidationDate = withdrawal.withdrawal.liquidation_date;
        String[] dateParts = liquidationDate.split("-");
        String liquidationDay = dateParts[1] + "/" + dateParts[2];
        String comment = withdrawal.withdrawal.comment;

        holder.itemName.setText(itemName);
        holder.payerName.setText(payerName);
        holder.price.setText((String) String.valueOf(price));
        holder.liquidationDate.setText(liquidationDay);
        holder.comment.setText(comment);
    }

    @Override
    public int getItemCount() {
        if(this.withdrawals == null){
            return 0;
        }
        return this.withdrawals.size();
    }
}
