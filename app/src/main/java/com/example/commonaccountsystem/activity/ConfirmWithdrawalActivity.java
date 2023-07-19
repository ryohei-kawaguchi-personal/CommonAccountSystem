package com.example.commonaccountsystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.view_util.PayerSummaryAdapter;
import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.model.MonthlyWithdrawal;
import com.example.commonaccountsystem.view_util.RecyclerViewClickListener;
import com.example.commonaccountsystem.view_util.WithdrawalAdapter;
import com.example.commonaccountsystem.view_model.ConfirmWithdrawalView;
import com.example.commonaccountsystem.view_model.ConfirmWithdrawalViewFactory;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class ConfirmWithdrawalActivity extends AppCompatActivity implements RecyclerViewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_withdrawal);

        displayTargetMonthlyWithdrawal(getThisYearMouth());
    }
    private YearMonth getThisYearMouth(){
        return YearMonth.now();
    }

    private void displayTargetMonthlyWithdrawal(YearMonth yearMonth){
        ViewModelProvider.Factory factory = new ConfirmWithdrawalViewFactory(this);
        ConfirmWithdrawalView viewModel = new ViewModelProvider(this, factory).get(ConfirmWithdrawalView.class);
        viewModel.prepareTargetMonthlyWithdrawal(yearMonth);

        viewModel.getMonthlyWithdrawalLiveMap().observe(this, new Observer<Map<YearMonth, MonthlyWithdrawal>>() {
            @Override
            public void onChanged(Map<YearMonth, MonthlyWithdrawal> monthlyWithdrawalMap) {
                YearMonth targetYearMonth = viewModel.getTargetYearMonth();
                display(targetYearMonth, monthlyWithdrawalMap.get(targetYearMonth));
            }
        });
    }
    private void display(YearMonth targetYearMonth, MonthlyWithdrawal monthlyWithdrawal){
        TextView title = (TextView) findViewById(R.id.confirm_withdrawal_title);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月", Locale.JAPANESE);
        String formattedYearMonth = targetYearMonth.format(formatter);
        title.setText(formattedYearMonth + getString(R.string.confirm_withdrawal_title));

        RecyclerView payerSummaryRView = (RecyclerView) findViewById(R.id.payer_summary_recycler_view);
        RecyclerView.Adapter payerSummaryAdapter = new PayerSummaryAdapter(monthlyWithdrawal.getPayerSummaries());
        payerSummaryRView.setAdapter(payerSummaryAdapter);
        payerSummaryRView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView withdrawalRView = (RecyclerView) findViewById(R.id.withdrawal_recycler_view);
        RecyclerView.Adapter withdrawalAdapter = new WithdrawalAdapter(monthlyWithdrawal.getWithdrawals(), this);
        withdrawalRView.setAdapter(withdrawalAdapter);
        withdrawalRView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setRecyclerView(RecyclerView rView, RecyclerView.Adapter adapter){
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickPrevMonthButton(View view){
        ViewModelProvider.Factory factory = new ConfirmWithdrawalViewFactory(this);
        ConfirmWithdrawalView viewModel = new ViewModelProvider(this, factory).get(ConfirmWithdrawalView.class);
        YearMonth targetYearMonth = viewModel.getTargetYearMonth().minusMonths(1);
        displayTargetMonthlyWithdrawal(targetYearMonth);
    }

    public void onClickNextMonthButton(View view){
        ViewModelProvider.Factory factory = new ConfirmWithdrawalViewFactory(this);
        ConfirmWithdrawalView viewModel = new ViewModelProvider(this, factory).get(ConfirmWithdrawalView.class);
        YearMonth targetYearMonth = viewModel.getTargetYearMonth().plusMonths(1);
        displayTargetMonthlyWithdrawal(targetYearMonth);
    }

    @Override
    public void onItemClick(WithdrawalWithItemAndPayer withdrawal) {
        Intent intent = new Intent(this, EditWithdrawalActivity.class);
        intent.putExtra("withdrawal", withdrawal);
        startActivity(intent);
    }

    public void onClickReturnHomeButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}