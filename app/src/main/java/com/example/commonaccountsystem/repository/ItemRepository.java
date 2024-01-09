package com.example.commonaccountsystem.repository;

import android.content.Context;

import com.example.commonaccountsystem.dao.AppDatabase;
import com.example.commonaccountsystem.dao.ItemDAO;
import com.example.commonaccountsystem.entity.Item;

import java.util.Comparator;
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
        return this.items.stream()
                //.filter(i -> i.payment_date == null)
                .sorted((i1,i2) -> Integer.compare(i1.displayOrder, i2.displayOrder))
                .map(i -> i.name)
                .collect(Collectors.toList());
    }

    public int fetchIdByName(String name){
        if(this.items == null){
            return dao.selectIdByName(name);
        }
        Optional<Integer> id = items.stream().filter(i -> i.name.equals(name)).map(i -> i.id).findFirst();
        return id.orElse(-1);
    }

    public int fetchCostByName(String name){
        int id = fetchIdByName(name);
        if(this.items == null){
            return dao.selectCostById(id);
        }
        Optional<Integer> cost = items.stream().filter(i -> i.id == id).map(i -> i.cost).findFirst();
        return cost.orElse(0);
    }
}
