package uk.co.connorglennon.androidengineerexercise.validation;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Connor Glennon on 19/11/2017.
 */

public abstract class FormActivity extends AppCompatActivity implements InputValidatorListener{

    @Override
    public void isValid(EditText editText) {
        EditTextHandler.setValid(editText, null);
        setFlag(editText, true);
        checkValidation();
    }

    @Override
    public void isInvalid(EditText editText, String message) {
        EditTextHandler.setInvalid(editText, message);
        setFlag(editText, false);
        checkValidation();
    }

    @Override
    public void hasNoData(EditText editText) {
        EditTextHandler.resetValid(editText);
        setFlag(editText, false);
        checkValidation();
    }

    abstract protected void setFlag(EditText editText, boolean flag);
    abstract protected void checkValidation();
}
