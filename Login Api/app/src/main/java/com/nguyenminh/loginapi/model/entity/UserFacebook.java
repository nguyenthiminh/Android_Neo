package com.nguyenminh.loginapi.model.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserFacebook extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String lastName;
    private String firstName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
