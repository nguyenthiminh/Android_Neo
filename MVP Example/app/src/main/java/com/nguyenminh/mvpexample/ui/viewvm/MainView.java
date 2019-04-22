package com.nguyenminh.mvpexample.ui.viewvm;

import com.nguyenminh.mvpexample.model.entity.ItemImage;

import java.util.List;

public interface MainView {
    void showList(List<ItemImage> itemImages);
}
