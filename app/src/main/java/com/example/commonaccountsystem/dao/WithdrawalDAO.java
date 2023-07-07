package com.example.commonaccountsystem.dao;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.commonaccountsystem.dto.Item;
import com.example.commonaccountsystem.dto.Payer;
import com.example.commonaccountsystem.dto.Withdrawal;
import com.example.commonaccountsystem.dto.WithdrawalWithItemAndPayer;

import java.util.List;
import java.util.Map;

@Dao
public interface WithdrawalDAO {
    @Insert
    public void insert(Withdrawal... withdrawals);

    @Query("SELECT p.name AS payerName, i.name AS itemName,i.cost, w.id, w.item_id, w.payer_id, w.price, w.liquidation_date, w.comment " +
            "FROM withdrawal AS w " +
            "JOIN item AS i ON w.item_id = i.id " +
            "JOIN payer AS p ON w.payer_id = p.id;")
    public List<WithdrawalWithItemAndPayer> selectByLiquidationDate();

    @Query("SELECT * FROM withdrawal")
    public List<Withdrawal> selectAll();

}
