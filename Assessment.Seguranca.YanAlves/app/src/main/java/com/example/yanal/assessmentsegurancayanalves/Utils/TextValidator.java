package com.example.yanal.assessmentsegurancayanalves.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by yanal on 06/10/2017.
 */

public abstract class TextValidator implements TextWatcher {
    private final EditText _editText;

    public TextValidator(EditText editText) {
        this._editText = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = _editText.getText().toString();
        validate(_editText, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { }
}