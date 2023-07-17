package com.example.commonaccountsystem.view_util;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.commonaccountsystem.R;

public class PayerSummaryViewHolder extends RecyclerView.ViewHolder{
    public TextView payerName;
    public TextView amount;
    public TextView difference;

    public PayerSummaryViewHolder(View itemView) {
        super(itemView);
        this.payerName = itemView.findViewById(R.id.payer_summary_payer);
        this.amount = itemView.findViewById(R.id.payer_summary_amount);
        this.difference = itemView.findViewById(R.id.payer_summary_difference);
    }
}
