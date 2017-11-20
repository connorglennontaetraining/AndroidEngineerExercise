package uk.co.connorglennon.androidengineerexercise.realm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import uk.co.connorglennon.androidengineerexercise.MyApp;
import uk.co.connorglennon.androidengineerexercise.R;

/**
 * Created by Connor Glennon on 20/11/2017.
 */

public class BitmapHandler {

    public static byte[] saveImage(int resourceId)
    {
        Bitmap bmp = BitmapFactory.decodeResource(MyApp.appContext.getResources(), resourceId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
