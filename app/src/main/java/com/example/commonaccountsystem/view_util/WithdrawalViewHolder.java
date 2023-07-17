package com.example.commonaccountsystem.view_util;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.commonaccountsystem.R;

public class WithdrawalViewHolder extends RecyclerView.ViewHolder{
    public TextView itemName;
    public TextView payerName;
    public TextView price;
    public TextView liquidationDate;
    public TextView comment;

    public WithdrawalViewHolder(View itemView) {
        super(itemView);
        this.itemName = itemView.findViewById(R.id.withdrawal_item);
        this.payerName = itemView.findViewById(R.id.withdrawal_payer);
        this.price = itemView.findViewById(R.id.withdrawal_price);
        this.liquidationDate = itemView.findViewById(R.id.withdrawal_liquidation_date);
        this.comment = itemView.findViewById(R.id.withdrawal_comment);
    }
}
