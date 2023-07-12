package com.example.commonaccountsystem.repository;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.WithdrawalDAO;
import com.example.commonaccountsystem.entity.Withdrawal;

import java.util.List;

public class WithdrawalRepository {
    private WithdrawalDAO dao;

    public WithdrawalRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        this.dao = db.withdrawalDAO();
    }

    public boolean register(Withdrawal withdrawal) {
        try{
            this.dao.insert(withdrawal);
            return true;
        }catch(SQLiteConstraintException e){
            return false;
        }
    }
}
