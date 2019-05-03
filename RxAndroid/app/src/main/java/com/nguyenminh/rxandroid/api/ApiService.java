package com.nguyenminh.rxandroid.api;

public class ApiService {
    public static ApiInterface getApiInterface() {
        return ApiClient.getRetrofit(Api.URL).create(ApiInterface.class);
    }
}
