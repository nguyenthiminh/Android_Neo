package com.nguyenminh.mvpexample.presenter;

import com.nguyenminh.mvpexample.model.DataImage;
import com.nguyenminh.mvpexample.model.LoadData;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.ui.viewvm.MainView;

import java.util.List;

public class ListViewPresenter implements LoadData {
    private DataImage dataImage;
    private MainView mainView;

    public ListViewPresenter(MainView mainView) {
        this.mainView = mainView;
        dataImage = new DataImage(this);
    }

    public void loadData() {
        dataImage.createListData1();
    }

    @Override
    public void onLoadDataSuccess(List<ItemImage> itemImages) {
        mainView.showList(itemImages);
    }
}
