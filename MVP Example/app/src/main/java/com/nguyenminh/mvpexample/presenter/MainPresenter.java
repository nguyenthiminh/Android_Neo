package com.nguyenminh.mvpexample.presenter;

import com.nguyenminh.mvpexample.model.DataImage;
import com.nguyenminh.mvpexample.model.LoadData;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.ui.viewVM.MainView;

import java.util.List;

public class MainPresenter implements LoadData {
    private DataImage dataImage;
    private MainView mainView;
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        dataImage = new DataImage(this);
    }

    public void loadData() {
        dataImage.createListData();
    }

    @Override
    public void onLoadDataSuccess(List<ItemImage> listDemo) {
        mainView.showList(listDemo);
    }
}
