package uk.co.connorglennon.androidengineerexercise.validation;

import android.text.InputType;
import android.widget.EditText;

import uk.co.connorglennon.androidengineerexercise.MyApp;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class EditTextHandler {

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

    public static EditText addValidator(InputValidatorListener activity_context, EditText editText, String regex, String message)
    {
        RegexValidatorTextWatcher regexValidatorTextWatcher = new RegexValidatorTextWatcher(editText, regex, message);
        regexValidatorTextWatcher.addListener(activity_context);
        editText.addTextChangedListener(regexValidatorTextWatcher);
        return editText;
    }

    public static EditText togglePassword(EditText editText)
    {
        if(editText.getInputType() == (InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD))
        {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            editText.setSelection(editText.getText().length());
        }
        else
        {
            editText.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText.setSelection(editText.getText().length());
        }
        return editText;
    }
}
