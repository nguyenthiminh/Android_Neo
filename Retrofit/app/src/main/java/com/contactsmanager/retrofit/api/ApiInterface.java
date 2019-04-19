package com.contactsmanager.retrofit.api;

import com.contactsmanager.retrofit.model.User;
import com.contactsmanager.retrofit.model.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/users")
    Call<List<User>> getUser();

    @GET("/users/{login}")
    Call<UserDetail> getUserDetail(@Path("login") String login);
}
