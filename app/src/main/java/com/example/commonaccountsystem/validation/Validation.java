package com.example.commonaccountsystem.validation;

import android.widget.EditText;

public abstract class Validation {
    protected boolean isValid;

    public Validation(){
        resetFlag();
    }

    public void resetFlag(){
        this.isValid = true;
    }

    public boolean getFlag(){
        return this.isValid;
    }
    public abstract void check(EditText editText);
}
