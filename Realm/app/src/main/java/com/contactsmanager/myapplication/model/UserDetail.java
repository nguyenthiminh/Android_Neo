package com.contactsmanager.myapplication.model;

import java.io.Serializable;

import io.realm.RealmObject;

public class UserDetail extends RealmObject {
    private int id;
    private String login;
    private String avatar;

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
}
