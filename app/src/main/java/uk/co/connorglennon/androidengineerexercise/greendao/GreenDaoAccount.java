/*
 * Copyright (c) 2017. Connor Glennon All rights reserved.
 * Licenced under the Attribution-NonCommercial-NoDerivatives 4.0 International (CC BY-NC-ND 4.0)
 * You may view the license at: https://creativecommons.org/licenses/by-nc-nd/4.0/
 */

package uk.co.connorglennon.androidengineerexercise.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * GreenDaoAccount holds data on accounts.
 */
@Entity(nameInDb = "account")
public class GreenDaoAccount {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "email")
    private String email;
    @Property(nameInDb = "password")
    private String password;
    @Generated(hash = 210551893)
    public GreenDaoAccount(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    @Generated(hash = 6757983)
    public GreenDaoAccount() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //TODO: Link account details.
    //private X accountDetails;


}
