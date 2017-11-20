package uk.co.connorglennon.androidengineerexercise.realm;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import uk.co.connorglennon.androidengineerexercise.AccountDetailsActivity;

/**
 * Created by Connor Glennon on 19/11/2017.
 */

public class RealmAccount extends RealmObject {
    /*
    * copyToRealmOrUpdate when there is a primary key.
    * define a primary key with @PrimaryKey.
     */

    /*
    * @NonNUll
     */
    String email;

    String password;

    RealmList<RealmAccountDetails> accountDetails;

    public RealmAccount() {
        this.accountDetails = new RealmList<>();
    }

    public RealmAccount(String email, String password) {
        this.email = email;
        this.password = password;
        this.accountDetails = new RealmList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmAccountDetails getAccountDetails() {
        if(this.accountDetails.size() <= 0) return null;
        else return this.accountDetails.get(0);
    }

    public void setAccountDetails(RealmAccountDetails accountDetails) {
        if(this.accountDetails.size() <= 0) this.accountDetails.add(accountDetails);
        else this.accountDetails.set(0, accountDetails);
    }
}
