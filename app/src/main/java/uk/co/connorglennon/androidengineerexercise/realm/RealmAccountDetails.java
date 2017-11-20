package uk.co.connorglennon.androidengineerexercise.realm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

/**
 * Created by Connor Glennon on 19/11/2017.
 */

public class RealmAccountDetails extends RealmObject {

    private String forename, surname, username, dob, age, country, gender, address;
    private byte[] profilePhoto;

    @LinkingObjects("accountDetails")
    private final RealmResults<RealmAccount> account = null;

    public RealmAccountDetails() {
    }

    public RealmAccountDetails(String forename, String surname, String username,
                               String dob, String age, String country, String gender,
                               String address, byte[] profilePhoto) {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.dob = dob;
        this.age = age;
        this.country = country;
        this.gender = gender;
        this.address = address;

        this.profilePhoto = profilePhoto;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bitmap getProfilePhoto() {
        return BitmapFactory.decodeByteArray(profilePhoto, 0, profilePhoto.length);
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
