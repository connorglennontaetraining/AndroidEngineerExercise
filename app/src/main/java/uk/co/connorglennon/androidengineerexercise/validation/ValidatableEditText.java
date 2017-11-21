package uk.co.connorglennon.androidengineerexercise.validation;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import uk.co.connorglennon.androidengineerexercise.MyApp;

/**
 * Created by Connor Glennon on 21/11/2017.
 */

public class ValidatableEditText {
    public EditText editText;
    private String input, invalidMessage;
    private ValidationMethod validationMethod;
    private ValidationMarkers validationMarkers;

    public ValidatableEditText(final EditText editText, final String invalidMessage, final ValidationMethod validationMethod, final ValidationMarkers validationMarkers) {
        this.editText = editText;
        input = "";
        this.invalidMessage = invalidMessage;
        this.validationMethod = validationMethod;
        this.validationMarkers = validationMarkers;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(MyApp.appContext, "Testing", Toast.LENGTH_SHORT).show();
                input = editText.getText().toString();
                if(validationMethod.isStringNUllOrEmpty(input))
                {
                    EditTextHandler.resetValid(editText);
                }
                else
                {
                    if(validationMethod.validate(input))
                    {
                        EditTextHandler.setValid(editText, null, validationMarkers.validMarker);
                    }
                    else {
                        EditTextHandler.setInvalid(editText, invalidMessage, validationMarkers.invalidMarker);
                    }
                }
            }
        });


    }

    public static class ValidationMarkers
    {
        private Drawable validMarker, invalidMarker;

        public ValidationMarkers(Drawable validMarker, Drawable invalidMarker) {
            this.validMarker = validMarker;
            this.invalidMarker = invalidMarker;
        }
    }

    public static abstract class ValidationMethod {
        public abstract boolean validate(String input);

        public final boolean isStringNUllOrEmpty(String input)
        {
            return input.isEmpty() || input == null;
        }
    }

    public static class RegexMethod extends ValidationMethod
    {
        String regex;

        public RegexMethod(String regex) {
            this.regex = regex;
        }

        /**
         * Validates the whole string against a regex. Doesn't guarantee
         * checks against substrings of the input.
         * @param input The string to be tested.
         * @return true if the input matches the regex
         * or, either param or the supplied regex is null or ""
         */
        @Override
        public boolean validate(String input) {
            if(isStringNUllOrEmpty(input)) return true;
            if(isStringNUllOrEmpty(input)) return true;
            if(input.matches(regex)) return true;
            return false;
        }
    }
}
