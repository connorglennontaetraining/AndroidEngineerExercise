package uk.co.connorglennon.androidengineerexercise;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class MyApp extends Application {
    public static Drawable tick, cross;
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        tick = getDrawable(R.drawable.tick);
        cross = getDrawable(R.drawable.cross);

        appContext = this;

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
