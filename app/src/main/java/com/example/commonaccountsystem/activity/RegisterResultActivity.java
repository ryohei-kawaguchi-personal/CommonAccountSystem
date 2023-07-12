package com.example.commonaccountsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.commonaccountsystem.R;

public class RegisterResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_result);
        TextView registerResultTextView = findViewById(R.id.register_result);
        registerResultTextView.setText(this.getIntent().getStringExtra("result"));
    }

    public void onClickContinueRegistrationButton(View view){
        Intent intent = new Intent(this, RegisterWithdrawalActivity.class);
        startActivity(intent);
    }

    public void onClickReturnHomeButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}