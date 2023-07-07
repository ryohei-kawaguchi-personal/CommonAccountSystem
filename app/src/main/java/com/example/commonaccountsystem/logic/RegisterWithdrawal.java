package com.example.commonaccountsystem.logic;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.ItemDAO;
import com.example.commonaccountsystem.dao.WithdrawalDAO;
import com.example.commonaccountsystem.dto.Item;
import com.example.commonaccountsystem.dto.Payer;
import com.example.commonaccountsystem.dto.Withdrawal;
import com.example.commonaccountsystem.dto.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.repository.WithdrawalRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegisterWithdrawal {
    public boolean register(Context context, Withdrawal withdrawal){
        WithdrawalRepository wRep = WithdrawalRepository.getInstance(context);
        return wRep.register(withdrawal);
    }
}