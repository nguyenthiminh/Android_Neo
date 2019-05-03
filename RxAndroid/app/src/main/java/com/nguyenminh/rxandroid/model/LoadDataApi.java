package com.nguyenminh.rxandroid.model;

import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;

import java.util.List;

public interface LoadDataApi {
    void loadDataPerson(List<Person> personList);
    void loadDataDetailPerson(DetailPerson detailPerson);
}
