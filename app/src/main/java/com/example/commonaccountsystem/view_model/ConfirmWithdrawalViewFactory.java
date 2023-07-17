package com.example.commonaccountsystem.view_model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ConfirmWithdrawalViewFactory implements ViewModelProvider.Factory {
    private Context context;
    public ConfirmWithdrawalViewFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // ViewModelのインスタンスを作成します
        if (modelClass.isAssignableFrom(ConfirmWithdrawalView.class)) {
            return (T) new ConfirmWithdrawalView(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
