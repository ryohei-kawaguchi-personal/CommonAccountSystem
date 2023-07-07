package com.example.commonaccountsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.activity.RegisterWithdrawalActivity;
import com.example.commonaccountsystem.dao.WithdrawalDAO;
import com.example.commonaccountsystem.dto.Item;
import com.example.commonaccountsystem.dto.Payer;
import com.example.commonaccountsystem.dto.Withdrawal;
import com.example.commonaccountsystem.dto.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.logic.RegisterWithdrawal;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickRegisterWithdrawalButton(View view){
        Intent intent = new Intent(this, RegisterWithdrawalActivity.class);
        startActivity(intent);
    }

    public void onClickConfirmWithdrawalButton(View view){
    }

    public void onClickSendDataButton(View view){
        //データ送信処理
    }
}