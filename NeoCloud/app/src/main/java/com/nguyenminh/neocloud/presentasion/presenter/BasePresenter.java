package com.nguyenminh.neocloud.presentasion.presenter;

import com.nguyenminh.neocloud.presentasion.view.BaseView;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
