package com.nguyenminh.rxandroid.model.entity;

import java.util.List;

public class UserAndDetail {
    private List<Person> personList;
    private DetailPerson detailPerson;

    public UserAndDetail(List<Person> personList, DetailPerson detailPerson) {
        this.personList = personList;
        this.detailPerson = detailPerson;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public DetailPerson getDetailPerson() {
        return detailPerson;
    }

    public void setDetailPerson(DetailPerson detailPerson) {
        this.detailPerson = detailPerson;
    }
}
