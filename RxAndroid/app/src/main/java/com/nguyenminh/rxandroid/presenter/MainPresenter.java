package com.nguyenminh.rxandroid.presenter;

import com.nguyenminh.rxandroid.model.Data;
import com.nguyenminh.rxandroid.model.LoadDataApi;
import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;
import com.nguyenminh.rxandroid.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements LoadDataApi, BasePresenter<MainView> {
    private MainView mainView;
    private Data data;
    public List<Person> personList = new ArrayList<>();

    public MainPresenter(){
        data = new Data(this);
    }

    @Override
    public void attachView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }

    public void getList(){
        data.getListPerson();
    }

    @Override
    public void loadDataPerson(List<Person> personList) {
        mainView.showList(personList);
    }

    @Override
    public void loadDataDetailPerson(DetailPerson detailPerson) {

    }
}
