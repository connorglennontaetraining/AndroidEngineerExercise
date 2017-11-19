package uk.co.connorglennon.androidengineerexercise.validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Connor Glennon on 19/11/2017.
 */

public class InputValidator implements TextWatcher{

    public static boolean validateString(String input, String regex)
    {
        if(regex.equals("")) return true;
        if(input.matches(regex))return true;
        else return false;
    }

    public static boolean checkEmpty(String input)
    {
        if(input == null)
        {
            return true;
        }
        else if(input.equals(""))
        {
            return true;
        }
        return false;
    }

    private EditText editText;
    private String regex, message;
    private ArrayList<InputValidatorListener> listeners;

    public InputValidator(EditText editText, String regex, String message) {
        this.editText = editText;
        this.regex = regex;
        this.message = message;
        this.listeners = new ArrayList<>();
    }

    public void addListener(InputValidatorListener listener)
    {
        this.listeners.add(listener);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(!checkEmpty(editable.toString()))
        {
            if(InputValidator.validateString(editable.toString(), regex)) {
                for(InputValidatorListener listener: listeners)
                {
                    listener.isValid(editText);
                }
            }
            else
            {
                for(InputValidatorListener listener: listeners)
                {
                    listener.isInvalid(editText, message);
                }
            }
        }
        else
        {
            for(InputValidatorListener listener: listeners)
            {
                listener.hasNoData(editText);
            }
        }
    }
}
