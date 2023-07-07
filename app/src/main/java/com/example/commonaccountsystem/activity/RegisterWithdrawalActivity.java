package com.example.commonaccountsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.commonaccountsystem.R;
import com.example.commonaccountsystem.logic.RegisterWithdrawal;
import com.example.commonaccountsystem.validation.Validation;
import com.example.commonaccountsystem.dto.Withdrawal;
import com.example.commonaccountsystem.validation.EmptyValidation;
import com.example.commonaccountsystem.repository.ItemRepository;
import com.example.commonaccountsystem.repository.PayerRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RegisterWithdrawalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_withdrawal);

        PayerRepository payerRepository = PayerRepository.getInstance(getApplicationContext());
        setSpinner(findViewById(R.id.payer_spinner), payerRepository.fetchAllNames());

        ItemRepository itemRepository = ItemRepository.getInstance(getApplicationContext());
        setSpinner(findViewById(R.id.item_spinner), itemRepository.fetchNamesWithVariableCost());
    }
    private void setSpinner(Spinner spinner, List<String> items){
        if(items != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_item,
                    items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    public void inputLiquidationDate(View liquidationDateText){
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                ((EditText)liquidationDateText).setText(String.format(Locale.JAPAN, "%02d / %02d / %02d", year, month + 1, dayOfMonth));
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void onClickRegistrationButton(View view){
        Validation validation = new EmptyValidation();
        Spinner itemSpinner = (Spinner) findViewById(R.id.item_spinner);
        String itemName = (String) itemSpinner.getSelectedItem();
        Spinner payerSpinner = (Spinner) findViewById(R.id.payer_spinner);
        String payerName = (String) payerSpinner.getSelectedItem();
        EditText price = (EditText) findViewById(R.id.price_edittext);
        validation.check(price);
        EditText liquidationDate = (EditText) findViewById(R.id.liquidation_date_edittext);
        validation.check(liquidationDate);
        EditText comment = (EditText) findViewById(R.id.comment_edittext);
        if(!validation.getFlag()){
            return ;
        }

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.itemId = ItemRepository.getInstance(getApplicationContext()).fetchIdByName(itemName);
        withdrawal.payerId = PayerRepository.getInstance(getApplicationContext()).fetchIdByName(payerName);
        withdrawal.price = Integer.parseInt(price.getText().toString());
        withdrawal.liquidation_date = liquidationDate.getText().toString();
        withdrawal.comment = comment.getText().toString();

        RegisterWithdrawal registerWithdrawal = new RegisterWithdrawal();
        Intent intent = new Intent(this, RegisterResultActivity.class);
        if(registerWithdrawal.register(this, withdrawal)){
            intent.putExtra("result", getString(R.string.register_success));

        }else{
            intent.putExtra("result", getString(R.string.register_failure));
        }
        startActivity(intent);
    }
}