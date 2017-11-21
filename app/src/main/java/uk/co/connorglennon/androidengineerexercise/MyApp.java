package uk.co.connorglennon.androidengineerexercise;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.connorglennon.androidengineerexercise.realm.RealmController;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class MyApp extends Application {
    public static Drawable tick, cross;
    public static Context appContext;

    public static byte[] key;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    public void onCreate() {
        super.onCreate();

        tick = getDrawable(R.drawable.tick);
        cross = getDrawable(R.drawable.cross);

        sharedPreferences = getSharedPreferences("uk.co.connorglennon.androidengineerexercise", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences.getString("ENCKEY", null) == null)
        {
            sharedPreferencesEditor.putString("ENCKEY", genEncKey()).apply();
        }

        appContext = this;

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(Base64.decode(sharedPreferences.getString("ENCKEY", null), Base64.DEFAULT))
                .build();

        Realm.setDefaultConfiguration(configuration);
    }

    private String genEncKey() {
        key = new byte[64];
        new SecureRandom().nextBytes(key);
        return Base64.encodeToString(key, Base64.DEFAULT);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        RealmController.getInstance().close();
    }

}
