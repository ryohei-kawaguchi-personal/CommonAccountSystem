package com.example.commonaccountsystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.commonaccountsystem.view_util.PayerSummaryAdapter;
import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.model.MonthlyWithdrawal;
import com.example.commonaccountsystem.view_util.WithdrawalAdapter;
import com.example.commonaccountsystem.view_model.ConfirmWithdrawalView;
import com.example.commonaccountsystem.view_model.ConfirmWithdrawalViewFactory;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class ConfirmWithdrawalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_withdrawal);

        displayTargetMonthlyWithdrawal(getThisYearMouth());
    }
    private YearMonth getThisYearMouth(){
        //TODO
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

        setRecyclerView((RecyclerView) findViewById(R.id.payer_summary_recycler_view), new PayerSummaryAdapter(monthlyWithdrawal.getPayerSummaries()));
        setRecyclerView((RecyclerView) findViewById(R.id.withdrawal_recycler_view), new WithdrawalAdapter(monthlyWithdrawal.getWithdrawals()));
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
}