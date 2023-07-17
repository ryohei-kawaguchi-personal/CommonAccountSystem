package com.example.commonaccountsystem.view_model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Index;

import com.example.commonaccountsystem.entity.Withdrawal;
import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.model.MonthlyWithdrawal;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmWithdrawalView extends ViewModel {
    private Context context;
    private YearMonth targetYearMonth;
    private MutableLiveData<Map<YearMonth, MonthlyWithdrawal>> monthlyWithdrawalLiveMap;

    public ConfirmWithdrawalView(Context context){
        this.context = context;
        this.monthlyWithdrawalLiveMap = new MutableLiveData<>();
        this.monthlyWithdrawalLiveMap.setValue(new HashMap<YearMonth, MonthlyWithdrawal>());
    }
    public void prepareTargetMonthlyWithdrawal(YearMonth yearMonth) {
        this.targetYearMonth = yearMonth;
        Map<YearMonth, MonthlyWithdrawal> monthlyWithdrawalMap = this.monthlyWithdrawalLiveMap.getValue();
        if(monthlyWithdrawalMap.containsKey(this.targetYearMonth)){
            return;
        }

        MonthlyWithdrawal monthlyWithdrawal = new MonthlyWithdrawal(this.context, this.targetYearMonth);
        monthlyWithdrawalMap.put(this.targetYearMonth, monthlyWithdrawal);
        monthlyWithdrawalLiveMap.setValue(monthlyWithdrawalMap);
    }

    public LiveData<Map<YearMonth, MonthlyWithdrawal>> getMonthlyWithdrawalLiveMap() {
        return monthlyWithdrawalLiveMap;
    }

    public YearMonth getTargetYearMonth(){
        return this.targetYearMonth;
    }
}
