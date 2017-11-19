package uk.co.connorglennon.androidengineerexercise;

import android.app.Application;
import android.graphics.drawable.Drawable;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class Constants {
    public static final String REGEX_EMAIL_RFC5332_OFFICIAL_STANDARD =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^;]{8,}$";
    public static final String REGEX_DOB = "(^(((0[1-9]|1[0-9]|2[0-8])[\\\\\\/](0[1-9]|1[012]))|((29|30|31)[\\\\\\/](0[13578]|1[02]))|((29|30)[\\\\\\/](0[4,6,9]|11)))[\\\\\\/](19|[2-9][0-9])\\\\d\\\\d$)|(^29[\\\\\\/]02[\\\\\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)";
}
