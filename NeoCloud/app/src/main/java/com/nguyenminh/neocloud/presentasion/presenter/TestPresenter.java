package com.nguyenminh.neocloud.presentasion.presenter;

import neo.vn.neocloud.presentasion.view.TestView;

public class TestPresenter implements BasePresenter<TestView> {
    public TestView view = null;

    @Override
    public void attachView(TestView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
