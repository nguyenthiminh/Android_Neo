package com.nguyenminh.googleapi.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserFacebook extends RealmObject {
    @PrimaryKey
    private String id;
    private String firstNname;
    private String lastName;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstNname() {
        return firstNname;
    }

    public void setFirstNname(String firstNname) {
        this.firstNname = firstNname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
