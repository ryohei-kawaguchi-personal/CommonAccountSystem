package com.example.commonaccountsystem.repository;

import android.content.Context;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.ItemDAO;
import com.example.commonaccountsystem.entity.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemRepository {
    private static ItemRepository instance;
    private ItemDAO dao;
    private List<Item> items;

    private ItemRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        this.dao = db.itemDAO();
    }

    public static ItemRepository getInstance(Context context){
        if(instance == null){
            instance = new ItemRepository(context);
        }
        return instance;
    }

    public List<Item> fetchAll(){
        if(this.items == null){
            this.items = dao.selectAll();
        }
        return this.items;
    }
    public List<String> fetchNamesWithVariableCost(){
        fetchAll();
        return this.items.stream().filter(i -> i.cost == 0).map(i -> i.name).collect(Collectors.toList());
    }

    public int fetchIdByName(String name){
        if(this.items == null){
            return dao.selectIdByName(name);
        }
        Optional<Integer> id = items.stream().filter(i -> i.name.equals(name)).map(i -> i.id).findFirst();
        return id.orElse(-1);
    }
}
