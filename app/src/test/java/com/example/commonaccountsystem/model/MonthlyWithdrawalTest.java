package com.example.commonaccountsystem.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import android.content.Context;

import com.example.commonaccountsystem.entity.Payer;
import com.example.commonaccountsystem.entity.Withdrawal;
import com.example.commonaccountsystem.entity.WithdrawalWithItemAndPayer;
import com.example.commonaccountsystem.repository.PayerRepository;
import com.example.commonaccountsystem.repository.WithdrawalRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MonthlyWithdrawal.class, PayerRepository.class})
public class MonthlyWithdrawalTest {
    @Mock
    private Context mockContext;
    @Mock
    private WithdrawalRepository mockWithdrawalRep;

    @Mock
    private PayerRepository mockPayerRep;
    private MonthlyWithdrawal monthlyWithdrawal;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        whenNew(WithdrawalRepository.class).withArguments(mockContext).thenReturn(mockWithdrawalRep);
        when(mockWithdrawalRep.fetchInYearMonth(YearMonth.of(2023, 7))).thenReturn(getSampleWithdrawals());

        mockStatic(PayerRepository.class);
        when(PayerRepository.getInstance(mockContext)).thenReturn(mockPayerRep);
        when(mockPayerRep.fetchAll()).thenReturn(getSamplePayers());
        monthlyWithdrawal = new MonthlyWithdrawal(mockContext, YearMonth.of(2023, 7));
    }

    private List<WithdrawalWithItemAndPayer> getSampleWithdrawals() {
        List<WithdrawalWithItemAndPayer> withdrawals = new ArrayList<WithdrawalWithItemAndPayer>();
        withdrawals.add(createWithdrawalWithItemAndPayer(1,1, 1, 100, "2023-07-15", "Comment 1", "食費", "ユーザーA"));
        withdrawals.add(createWithdrawalWithItemAndPayer(2,1, 1, 200, "2023-07-15", "Comment 2", "食費", "ユーザーA"));
        withdrawals.add(createWithdrawalWithItemAndPayer(3,1, 1, 300, "2023-07-15", "Comment 3", "食費", "ユーザーA"));
        withdrawals.add(createWithdrawalWithItemAndPayer(4,1, 2, 400, "2023-07-15", "Comment 4", "食費", "ユーザーB"));
        withdrawals.add(createWithdrawalWithItemAndPayer(5,1, 2, 500, "2023-07-15", "Comment 5", "食費", "ユーザーB"));
        withdrawals.add(createWithdrawalWithItemAndPayer(6,1, 2, 600, "2023-07-15", "Comment 6", "食費", "ユーザーB"));
        return withdrawals;
    }

    private WithdrawalWithItemAndPayer createWithdrawalWithItemAndPayer(int id, int itemId, int payerId, int price, String liquidation_date, String comment, String itemName, String payerName){
        WithdrawalWithItemAndPayer withdrawalWithItemAndPayer = new WithdrawalWithItemAndPayer();
        withdrawalWithItemAndPayer.withdrawal = createWithdrawal(id, itemId, payerId, price, liquidation_date, comment);
        withdrawalWithItemAndPayer.itemName = itemName;
        withdrawalWithItemAndPayer.payerName = payerName;
        return withdrawalWithItemAndPayer;
    }

    private Withdrawal createWithdrawal(int id, int itemId, int payerId, int price, String liquidation_date, String comment) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.id = id;
        withdrawal.itemId = itemId;
        withdrawal.payerId = payerId;
        withdrawal.price = price;
        withdrawal.liquidationDate = liquidation_date;
        withdrawal.comment = comment;
        return withdrawal;
    }

    private List<Payer> getSamplePayers() {
        List<Payer> payers = new ArrayList<>();
        payers.add(createPayer(1, "ユーザーA"));
        payers.add(createPayer(2, "ユーザーB"));
        return payers;
    }

    private Payer createPayer(int id, String name){
        Payer payer = new Payer();
        payer.id = id;
        payer.name = name;
        return payer;
    }

    @Test
    public void testFetchTargetWithdrawal() {
        List<WithdrawalWithItemAndPayer> withdrawals = monthlyWithdrawal.getWithdrawals();
        assertEquals(6, withdrawals.size());
    }

    @Test
    public void testFetchPayerSummary(){
        List<PayerSummary> payerSummaries = monthlyWithdrawal.getPayerSummaries();
        assertEquals(2, payerSummaries.size());

        Optional<PayerSummary> payerSummaryOptionalA = payerSummaries.stream().filter(ps -> ps.getPayerName().equals("ユーザーA")).findFirst();
        PayerSummary payerSummaryA = payerSummaryOptionalA.orElse(null);

        assertEquals(600, payerSummaryA.getAmount());
        assertEquals(-450, payerSummaryA.getDifference());

        Optional<PayerSummary> payerSummaryOptionalB = payerSummaries.stream().filter(ps -> ps.getPayerName().equals("ユーザーB")).findFirst();
        PayerSummary payerSummaryB = payerSummaryOptionalB.orElse(null);

        assertEquals(1500, payerSummaryB.getAmount());
        assertEquals(450, payerSummaryB.getDifference());
    }

    @Test
    public void getTargetYearMouth() {
        YearMonth yearMonth = monthlyWithdrawal.getTargetYearMouth();
        assertEquals(2023, yearMonth.getYear());
        assertEquals(7, yearMonth.getMonthValue());
    }

}