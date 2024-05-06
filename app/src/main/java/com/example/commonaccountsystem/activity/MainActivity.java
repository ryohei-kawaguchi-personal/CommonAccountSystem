package com.example.commonaccountsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.repository.WithdrawalRepository;

import java.util.List;

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
        Intent intent = new Intent(this, ConfirmWithdrawalActivity.class);
        startActivity(intent);
    }

    public void onClickSendDataButton(View view){
        //データ送信処理
        AppDatabase.closeDB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.closeDB();
    }

}