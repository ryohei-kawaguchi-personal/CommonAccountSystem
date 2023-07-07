package com.example.commonaccountsystem.validation;

import android.graphics.Color;
import android.widget.EditText;

import com.example.commonaccountsystem.validation.Validation;

public class EmptyValidation extends Validation {
    private String colorCode = "#FF9999";
    private String hintMessage = "入力してください";

    @Override
    public void check(EditText editText){
        if(editText.getText().toString().isEmpty()){
            editText.setHintTextColor(Color.parseColor(colorCode));
            editText.setHint(hintMessage);
            this.isValid = false;
        }
    }
}
