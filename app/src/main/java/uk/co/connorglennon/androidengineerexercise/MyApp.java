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
import uk.co.connorglennon.androidengineerexercise.greendao.DaoMaster;
import uk.co.connorglennon.androidengineerexercise.greendao.DaoSession;
import uk.co.connorglennon.androidengineerexercise.realm.RealmController;

/**
 * Created by Connor Glennon on 18/11/2017.
 */

public class MyApp extends Application {

    ///////////////////////////////////////////////////////////////////////////////
    // Static variables.
    ///////////////////////////////////////////////////////////////////////////////
    /* Application context. */
    public static Context appContext;

    /* Shared preferences. */
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;

    /* Global drawables. */
    public static Drawable tick, cross;



    ///////////////////////////////////////////////////////////////////////////////
    // Member variables.
    ///////////////////////////////////////////////////////////////////////////////
    /* GreenDao specific variables */
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // avoid moving this initialisation.
        appContext = this;
        init();
    }



    ///////////////////////////////////////////////
    /* Init section */
    ///////////////////////////////////////////////

    /**
     * initialises all the necessary pieces of the app
     * that are required when the app starts.
     * Add any init functionality here by creating an call
     * to your init method and adding it to the list.
     * be careful of the order of inits.
     */
    public void init()
    {
        // Always call this method as high up as possible.
        initSharedPreferences();
        initGlobalDrawables();
        initAsycn();
    }

    /**
     * Asynchronous method to speed up application start time.
     * Add code that can run in the background with no errors
     * and doesn't need to completed by the time onCreate is finished.
     */
    private void initAsycn()
    {
        Thread initThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // initialise Realm database.
                initRealm();

                //initialise GreenDAO
                //initGreenDao();
            }
        });
        initThread.start();
    }

    /**
     * Some drawables are used programmatically in various parts of the application.
     * Defined here they are less likely to be created more than once.
     * They can be used in methods with a context to call 'getDrawable(id)'
     */
    private void initGlobalDrawables()
    {
        tick = getDrawable(R.drawable.tick);
        cross = getDrawable(R.drawable.cross);
    }

    /**
     * initialises the shared preferences objects of this class.
     * Use this method to initialise any shared preferences that
     * need setting at application creation time. Don't bloat the
     * onCreate() method with preference creation.
     */
    private void initSharedPreferences()
    {
        sharedPreferences = getSharedPreferences("uk.co.connorglennon.androidengineerexercise", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    /**
     * initialises realm with encryption enabled.
     */
    private void initRealm()
    {
        //TODO: look at moving this key to a secure location.
        if(sharedPreferences.getString("ENCKEY", null) == null)
        {
            byte[] key = new byte[64];
            new SecureRandom().nextBytes(key);
            String enckey = Base64.encodeToString(key, Base64.DEFAULT);
            sharedPreferencesEditor.putString("ENCKEY", enckey).apply();
        }

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .encryptionKey(Base64.decode(sharedPreferences.getString("ENCKEY", null), Base64.DEFAULT))
                .build();

        Realm.setDefaultConfiguration(configuration);
    }

    /**
     * initialises GreenDao
     */
    private  void initGreenDao() {
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db")
                        .getWritableDb()).newSession();
    }
}
