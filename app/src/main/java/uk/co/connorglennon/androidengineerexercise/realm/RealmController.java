package uk.co.connorglennon.androidengineerexercise.realm;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import uk.co.connorglennon.androidengineerexercise.MyApp;

/**
 * Created by Connor Glennon on 19/11/2017.
 */

public class RealmController {

    private static RealmController instance = null;

    public static RealmController getInstance()
    {
        synchronized (RealmController.class)
        {
            if(instance == null)
            {
                synchronized (RealmController.class)
                {
                    instance = new RealmController(Realm.getDefaultInstance());
                }
            }
        }
        return instance;
    }

    Realm realm;

    private RealmController(Realm realm) {
        this.realm = realm;
    }

    public String getPath()
    {
        return realm.getPath();
    }

    public void saveAccount(final RealmAccount realmAccount)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmAccount);
            }
        });
    }

    public void saveAccountDetails(final RealmAccount realmAccount, final RealmAccountDetails realmAccountDetails)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmAccount.setAccountDetails(realmAccountDetails);
            }
        });
    }

    public RealmAccount getAccount(String email)
    {
        if(isEmailUsed(email))
        {
            return realm.where(RealmAccount.class)
                    .contains("email", email)
                    .findFirst();
        } return null;
    }

    public boolean isEmailUsed(String email)
    {
        RealmResults<RealmAccount> results = realm.where(RealmAccount.class)
                        .contains("email", email)
                        .findAll();
        Log.i("realm", "Size: " + results.size());
        if(results.size() > 0) return true;
        else return false;
    }

    public ArrayList<RealmAccount> getAllAccounts()
    {
        ArrayList<RealmAccount> accounts = new ArrayList<>();

        RealmResults<RealmAccount> accountsResults =
                realm.where(RealmAccount.class).findAll();

        for(RealmAccount customer: accountsResults)
        {
            accounts.add(customer);
        }

        return accounts;
    }

    public synchronized void close()
    {
        instance = null;
        realm.close();
    }
}
