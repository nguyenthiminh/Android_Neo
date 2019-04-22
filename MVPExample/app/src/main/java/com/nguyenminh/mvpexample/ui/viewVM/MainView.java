package com.nguyenminh.mvpexample.ui.viewVM;

import com.nguyenminh.mvpexample.model.entity.ItemImage;

import java.util.List;

public interface MainView {
    void showList(List<ItemImage> listDemo);
}
