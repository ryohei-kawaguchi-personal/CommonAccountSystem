package com.example.commonaccountsystem.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import com.example.commonaccountsystem.entity.Payer;
import com.example.commonaccountsystem.entity.Withdrawal;

public class PayerSummaryTest {
    private PayerSummary payerSummary;

    @Before
    public void setUp() {
        Payer payer = new Payer();
        payer.id = 1;
        payer.name = "ユーザーA";
        List<Withdrawal> personalWithdrawals = new ArrayList<>();
        personalWithdrawals.add(createWithdrawal(1, 1, 1, 100, "2023-07-15", "Comment 1"));
        personalWithdrawals.add(createWithdrawal(2, 2, 1, 200, "2023-07-16", "Comment 2"));
        personalWithdrawals.add(createWithdrawal(3, 3, 1, 300, "2023-07-17", "Comment 3"));
        payerSummary = new PayerSummary(payer, personalWithdrawals);
    }

    private Withdrawal createWithdrawal(int id, int itemId, int payerId, int price, String liquidation_date, String comment) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.id = id;
        withdrawal.itemId = itemId;
        withdrawal.payerId = payerId;
        withdrawal.price = price;
        withdrawal.liquidation_date = liquidation_date;
        withdrawal.comment = comment;
        return withdrawal;
    }

    @Test
    public void testCalcPositiveDifference() {
        payerSummary.calcDifference(200);
        int expectedDifference = 400;
        assertEquals(expectedDifference, payerSummary.getDifference());
    }

    @Test
    public void testCalcNegativeDifference() {
        payerSummary.calcDifference(800);
        int expectedDifference = -200;
        assertEquals(expectedDifference, payerSummary.getDifference());
    }

    @Test
    public void testGetPayerName() {
        String expectedName = "ユーザーA";
        assertEquals(expectedName, payerSummary.getPayerName());
    }

    @Test
    public void testGetAmount() {
        int expectedAmount = 600;
        assertEquals(expectedAmount, payerSummary.getAmount());
    }
}
