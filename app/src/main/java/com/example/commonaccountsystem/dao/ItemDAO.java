package com.example.commonaccountsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.commonaccountsystem.dto.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Query("SELECT * FROM item")
    public List<Item> selectAll();

    @Query("SELECT id FROM item WHERE name = :name")
    public int selectIdByName(String name);

}
