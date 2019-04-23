package com.nguyenminh.mvpexample.presenter;

interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
