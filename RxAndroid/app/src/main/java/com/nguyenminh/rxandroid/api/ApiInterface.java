package com.nguyenminh.rxandroid.api;

import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/users")
    Observable<List<Person>> getPerson();

    @GET("/users/{login}")
    Observable<DetailPerson> getDeail(@Path ("login") String login);
}
