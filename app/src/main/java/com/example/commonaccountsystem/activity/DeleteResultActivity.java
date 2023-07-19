package com.example.commonaccountsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.commonaccountsystem.R;

public class DeleteResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_result);
        TextView deleteResultTextView = findViewById(R.id.delete_result);
        deleteResultTextView.setText(this.getIntent().getStringExtra("result"));
    }

    public void onClickReturnConfirmButton(View view){
        Intent intent = new Intent(this, ConfirmWithdrawalActivity.class);
        startActivity(intent);
    }
}