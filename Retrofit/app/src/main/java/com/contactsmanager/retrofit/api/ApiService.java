package com.contactsmanager.retrofit.api;

public class ApiService {

    public static ApiInterface getApiInterface() {
        return ApiClient.getClient(Api.URL).create(ApiInterface.class);
    }
}
