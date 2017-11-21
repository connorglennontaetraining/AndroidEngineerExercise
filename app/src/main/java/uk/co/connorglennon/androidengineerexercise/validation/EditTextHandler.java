package uk.co.connorglennon.androidengineerexercise.validation;

import android.graphics.drawable.Drawable;
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

    public static EditText setInvalid(EditText editText, String message, Drawable invalidDrawable)
    {
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, invalidDrawable, null);
        editText.setError(message, null);
        return  editText;
    }

    public static EditText setValid(EditText editText, String message, Drawable validDrawable)
    {
        editText.setError(null);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, validDrawable, null);
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
