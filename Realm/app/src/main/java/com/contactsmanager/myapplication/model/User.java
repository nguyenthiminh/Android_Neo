package com.contactsmanager.myapplication.model;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {
    private int id;
    private String login;
    private String avatar;
    private String url;
    private RealmList<UserDetail> userDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RealmList<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(RealmList<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }
}
