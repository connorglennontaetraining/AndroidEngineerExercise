package uk.co.connorglennon.androidengineerexercise;

import android.app.Application;
import android.graphics.drawable.Drawable;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class MyApp extends Application {
    public static Drawable tick, cross;

    @Override
    public void onCreate() {
        super.onCreate();

        tick = getDrawable(R.drawable.tick);
        cross = getDrawable(R.drawable.cross);
    }
}
