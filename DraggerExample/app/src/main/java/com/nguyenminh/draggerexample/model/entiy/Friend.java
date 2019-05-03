package com.nguyenminh.draggerexample.model.entiy;

public class Friend {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void familiarly(String nameNew){
        name = nameNew;
    }

    public void unFamiliarly(){
        name = name;
    }
}
