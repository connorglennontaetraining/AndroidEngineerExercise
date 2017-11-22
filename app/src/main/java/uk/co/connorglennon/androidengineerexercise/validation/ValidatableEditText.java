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
    private boolean isValid;

    public ValidatableEditText(final EditText editText, final String invalidMessage, final ValidationMethod validationMethod, final ValidationMarkers validationMarkers) {
        this.editText = editText;
        input = "";
        this.invalidMessage = invalidMessage;
        this.validationMethod = validationMethod;
        this.validationMarkers = validationMarkers;
        this.isValid = false;

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
                if(validationMethod.isStringNUllOrEmpty(input)) {
                    resetValid(editText);
                    isValid = false;
                }
                else {
                    if(validationMethod.validate(input)) {
                        setValid(editText, null, validationMarkers.validMarker);
                        isValid = true;
                    }
                    else {
                        setInvalid(editText, invalidMessage, validationMarkers.invalidMarker);
                        isValid = false;
                    }
                }
            }
        });
    }

    public boolean isValid() {
        return isValid;
    }

    public static EditText resetValid(EditText editText) {
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        editText.setError(null);
        return editText;
    }

    /**
     * Modifies the EditText's look to indicate there is an error.
     * @param editText the EditText object to be modified.
     * @param message the error message that will be displayed to the user.
     * @param invalidDrawable a drawable that will be drawn on the right edge of
     *                        the object. If null, this method calls the standard
     *                        android setError(message).
     * @return the modified EditText.
     */
    public static EditText setInvalid(EditText editText, String message, Drawable invalidDrawable) {
        if(invalidDrawable != null) {
            editText.setCompoundDrawablesWithIntrinsicBounds(null, null, invalidDrawable, null);
            editText.setError(message, null);
        }
        else {
            editText.setError(message);
        }
        return  editText;
    }

    public static EditText setValid(EditText editText, String message, Drawable validDrawable) {
        editText.setError(null);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, validDrawable, null);
        return editText;
    }

    public static class ValidationMarkers {
        private Drawable validMarker, invalidMarker;

        public ValidationMarkers(Drawable validMarker, Drawable invalidMarker) {
            this.validMarker = validMarker;
            this.invalidMarker = invalidMarker;
        }
    }

    public static abstract class ValidationMethod {
        public abstract boolean validate(String input);

        public final boolean isStringNUllOrEmpty(String input) {
            return input.isEmpty() || input == null;
        }
    }

    public static class RegexMethod extends ValidationMethod {
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
