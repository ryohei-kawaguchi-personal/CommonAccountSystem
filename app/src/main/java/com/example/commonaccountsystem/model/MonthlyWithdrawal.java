package com.example.commonaccountsystem.model;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.commonaccountsystem.activity.ConfirmWithdrawalActivity;
import com.example.commonaccountsystem.entity.Payer;
import com.example.commonaccountsystem.entity.Withdrawal;
import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.repository.PayerRepository;
import com.example.commonaccountsystem.repository.WithdrawalRepository;
import com.example.commonaccountsystem.view_model.ConfirmWithdrawalView;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonthlyWithdrawal {
    private YearMonth targetYearMouth;
    private List<WithdrawalWithItemAndPayer> withdrawals;
    private List<PayerSummary> payerSummaries;

    public MonthlyWithdrawal(Context context, YearMonth targetYearMouth){
        this.targetYearMouth = targetYearMouth;
        fetchTargetWithdrawal(context);
        if(this.withdrawals != null){
            fetchPayerSummary(context);
        }
    }

    private void fetchTargetWithdrawal(Context context){
        if(this.withdrawals != null){
            return;
        }
        WithdrawalRepository wRep = new WithdrawalRepository(context);
        this.withdrawals = wRep.fetchInYearMonth(this.targetYearMouth);
    }

    private void fetchPayerSummary(Context context){
        if(this.payerSummaries != null){
            return;
        }

        PayerRepository pRep = PayerRepository.getInstance(context);
        List<Payer> payers = pRep.fetchAll();
        if(payers == null || payers.size() == 0){
            return;
        }

        payerSummaries = new ArrayList<PayerSummary>();
        for(Payer payer: payers){
            List<Withdrawal> personalWithdrawals = this.withdrawals.stream()
                    .filter(w -> w.withdrawal.payerId == payer.id)
                    .map(w -> w.withdrawal)
                    .collect(Collectors.toList());
            PayerSummary payerSummary = new PayerSummary(payer, personalWithdrawals);
            this.payerSummaries.add(payerSummary);
        }

        int totalAmount = this.payerSummaries.stream().mapToInt(ps -> ps.getAmount()).sum();
        int avgAmount = totalAmount / payers.size();
        this.payerSummaries.stream().forEach(ps -> ps.calcDifference(avgAmount));
    }

    public YearMonth getTargetYearMouth() {
        return this.targetYearMouth;
    }

    public List<WithdrawalWithItemAndPayer> getWithdrawals(){
        return this.withdrawals;
    }

    public List<PayerSummary> getPayerSummaries(){
        return this.payerSummaries;
    }
}
