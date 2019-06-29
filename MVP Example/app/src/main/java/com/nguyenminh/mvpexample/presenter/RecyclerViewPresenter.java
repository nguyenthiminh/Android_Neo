package com.nguyenminh.mvpexample.presenter;

import com.nguyenminh.mvpexample.model.DataImage;
import com.nguyenminh.mvpexample.model.LoadData;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.ui.viewvm.MainView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecyclerViewPresenter implements LoadData, Presenter<MainView> {
    private DataImage dataImage;
    private MainView mainView;
    private Calendar calendar;
    public ArrayList<ItemImage> listImage = new ArrayList<>();
    public RecyclerViewPresenter() {
        dataImage = new DataImage(this);
        calendar = Calendar.getInstance();
    }

    public void loadData() {
        dataImage.createListData();
    }

    @Override
    public void onLoadDataSuccess(List<ItemImage> itemImages) {
        mainView.showList(itemImages);
    }

    @Override
    public void attachView(MainView view) {
        this.mainView= view;
    }

    @Override
    public void detachView() {
        this.mainView= null;
    }
}
