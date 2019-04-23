package com.nguyenminh.mvpexample.presenter;

import com.nguyenminh.mvpexample.model.DataImage;
import com.nguyenminh.mvpexample.model.LoadData;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.ui.viewvm.MainView;

import java.util.ArrayList;
import java.util.List;

public class GridViewPresenter implements LoadData, Presenter<MainView> {
    private MainView mainView;
    private DataImage dataImage;
    public List<ItemImage> itemImages = new ArrayList<>();

    public GridViewPresenter() {
        dataImage = new DataImage(this);
    }

    public void loadData(){
        dataImage.createListData2();
    }

    @Override
    public void onLoadDataSuccess(List<ItemImage> itemImages) {
        mainView.showList(itemImages);
    }

    @Override
    public void attachView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }
}
