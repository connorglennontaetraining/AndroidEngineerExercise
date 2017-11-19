package uk.co.connorglennon.androidengineerexercise.validation;

import android.widget.EditText;

/**
 * Created by Connor Glennon on 19/11/2017.
 */

public interface InputValidatorListener {
    public void isValid(EditText editText);
    public void isInvalid(EditText editText, String message);
    public void hasNoData(EditText editText);
}
