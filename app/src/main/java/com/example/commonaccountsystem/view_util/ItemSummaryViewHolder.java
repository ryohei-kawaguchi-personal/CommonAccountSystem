package com.example.commonaccountsystem.view_util;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.commonaccountsystem.R;

public class ItemSummaryViewHolder extends RecyclerView.ViewHolder{
    public TextView itemName;
    public TextView amount;

    public ItemSummaryViewHolder(View itemView) {
        super(itemView);
        this.itemName = itemView.findViewById(R.id.item_summary_item);
        this.amount = itemView.findViewById(R.id.item_summary_amount);
    }
}
