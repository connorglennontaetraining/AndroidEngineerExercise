package uk.co.connorglennon.androidengineerexercise.validation;

import android.widget.Button;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class ButtonFormHandler {

    public static Button disableButton(Button btn)
    {
        btn.setEnabled(false);
        btn.setAlpha(0.5f);
        return btn;
    }

    public  static  Button enableButton(Button btn)
    {
        btn.setEnabled(true);
        btn.setAlpha(1.0f);
        return btn;
    }

}
