package com.example.commonaccountsystem.repository;

import android.content.Context;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.PayerDAO;
import com.example.commonaccountsystem.dto.Item;
import com.example.commonaccountsystem.dto.Payer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PayerRepository {
    private static PayerRepository instance;
    private PayerDAO dao;
    private List<Payer> payers;

    private PayerRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        this.dao = db.payerDAO();
    }

    public static PayerRepository getInstance(Context context){
        if(instance == null){
            instance = new PayerRepository(context);
        }
        return instance;
    }

    public List<Payer> fetchAll(){
        if(this.payers == null){
            this.payers = dao.selectAll();
        }
        return this.payers;
    }

    public List<String> fetchAllNames(){
        fetchAll();
        return this.payers.stream().map(p -> p.name).collect(Collectors.toList());
    }

    public int fetchIdByName(String name){
        if(this.payers == null){
            return dao.selectIdByName(name);
        }
        Optional<Integer> id = payers.stream().filter(p -> p.name.equals(name)).map(p -> p.id).findFirst();
        return id.orElse(-1);
    }
}
