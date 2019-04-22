package com.nguyenminh.mvpexample.model;

import com.nguyenminh.mvpexample.model.entity.ItemImage;

import java.util.List;

public interface LoadData {
    void onLoadDataSuccess(List<ItemImage> itemImages);
}
