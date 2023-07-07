package com.example.commonaccountsystem.dao;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.commonaccountsystem.dto.Payer;

import java.util.List;

@Dao
public interface PayerDAO {
    @Query("SELECT * FROM payer")
    public List<Payer> selectAll();

    @Query("SELECT id FROM payer WHERE name = :name")
    public int selectIdByName(String name);
}
