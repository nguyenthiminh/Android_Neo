package com.nguyenminh.rxandroid.presenter;

interface BasePresenter<V> {
    void attachView(V view);
    void detachView();
}
