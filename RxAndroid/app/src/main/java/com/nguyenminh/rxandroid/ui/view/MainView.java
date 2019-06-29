package com.nguyenminh.rxandroid.ui.view;

import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;

import java.util.List;

public interface MainView {
    void showList(List<Person> personList);

    void displayResult(DetailPerson person);

    void displayError(String error);
}
