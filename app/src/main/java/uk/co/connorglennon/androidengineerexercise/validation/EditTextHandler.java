package uk.co.connorglennon.androidengineerexercise.validation;

import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.widget.EditText;

import javax.annotation.Nullable;

import uk.co.connorglennon.androidengineerexercise.MyApp;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class EditTextHandler {



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
