package uk.co.connorglennon.androidengineerexercise.validation;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import uk.co.connorglennon.androidengineerexercise.Constants;
import uk.co.connorglennon.androidengineerexercise.MyApp;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class EditTextFormHandler {

    public static EditText resetValid(EditText editText)
    {
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        editText.setError(null);
        return editText;
    }

    public static EditText setInvalid(EditText editText, String message)
    {
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, MyApp.cross, null);
        editText.setError(message, null);
        return  editText;
    }

    public static EditText setValid(EditText editText, String message)
    {
        editText.setError(null);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, MyApp.tick, null);
        return editText;
    }

    public static EditText addValidator(InputValidatorListener acyivity_context, EditText editText, String regex, String message)
    {
        InputValidator inputValidator = new InputValidator(editText, "", message);
        inputValidator.addListener(acyivity_context);
        editText.addTextChangedListener(inputValidator);
        return editText;
    }
}
