package com.example.commonaccountsystem.repository;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.WithdrawalDAO;
import com.example.commonaccountsystem.entity.Withdrawal;
import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class WithdrawalRepository {
    private WithdrawalDAO dao;

    public WithdrawalRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        if(db != null){
            this.dao = db.withdrawalDAO();
        }
    }

    public boolean register(Withdrawal withdrawal) {
        try{
            this.dao.insert(withdrawal);
            return true;
        }catch(SQLiteConstraintException e){
            return false;
        }
    }

    public List<WithdrawalWithItemAndPayer> fetchInYearMonth(YearMonth yearMonth){
        if(yearMonth == null){
            return null;
        }

        String formattedMonth = String.format("%02d", yearMonth.minusMonths(1).getMonthValue());
        String formattedYearPrevMonth = yearMonth.getYear() + "-" + formattedMonth;
        formattedMonth = String.format("%02d", yearMonth.getMonthValue());
        String formattedYearMonth = yearMonth.getYear() + "-" + formattedMonth;
        return this.dao.selectInYearMonth(formattedYearPrevMonth, formattedYearMonth);
    }

    public boolean delete(Withdrawal withdrawal){
        try{
            this.dao.delete(withdrawal);
            return true;
        }catch(SQLiteConstraintException e){
            return false;
        }
    }
}
