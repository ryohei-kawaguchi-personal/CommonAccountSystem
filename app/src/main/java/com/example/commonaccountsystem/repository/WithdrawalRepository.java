package com.example.commonaccountsystem.repository;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.ItemDAO;
import com.example.commonaccountsystem.dao.WithdrawalDAO;
import com.example.commonaccountsystem.dto.Item;
import com.example.commonaccountsystem.dto.Withdrawal;

import java.util.List;

public class WithdrawalRepository {
    private static WithdrawalRepository instance;
    private WithdrawalDAO dao;
    private List<Withdrawal> withdrawals;

    private WithdrawalRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        this.dao = db.withdrawalDAO();
    }

    public static WithdrawalRepository getInstance(Context context){
        if(instance == null){
            instance = new WithdrawalRepository(context);
        }
        return instance;
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
