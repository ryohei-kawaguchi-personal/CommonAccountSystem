package com.example.commonaccountsystem.dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.entity.Item;
import com.example.commonaccountsystem.entity.Payer;
import com.example.commonaccountsystem.entity.Withdrawal;

@Database(entities = {Item.class, Payer.class, Withdrawal.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null) {
            if(context.deleteDatabase(context.getString(R.string.dbname))){
                System.out.println("DBを削除しました。");
            }

            instance = Room.databaseBuilder(context, AppDatabase.class, context.getString(R.string.dbname))
                    .createFromAsset(context.getString(R.string.dbname)+ ".db")
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
    public abstract ItemDAO itemDAO();
    public abstract PayerDAO payerDAO();
    public abstract WithdrawalDAO withdrawalDAO();
}
