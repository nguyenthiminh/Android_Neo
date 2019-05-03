package com.nguyenminh.rxandroid.model;

import android.util.Log;
import android.util.Pair;

import com.nguyenminh.rxandroid.api.ApiClient;
import com.nguyenminh.rxandroid.api.ApiInterface;
import com.nguyenminh.rxandroid.api.ApiService;
import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;
import com.nguyenminh.rxandroid.model.entity.UserAndDetail;

import java.util.ArrayList;
import java.util.List;


import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

import rx.Subscriber;
import rx.functions.Func2;

import static android.support.constraint.Constraints.TAG;

public class Data {
    private LoadDataApi loadDataApi;
    private List<Person> personArrayList = new ArrayList<>();
    private DetailPerson detailPerson = new DetailPerson();

    public Data(LoadDataApi loadDataApi) {
        this.loadDataApi = loadDataApi;
    }

    public void getListPerson() {
        /** Call one api
        ApiService.getApiInterface().getPerson()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Person>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Person> personList) {
                        if (personArrayList.size() == 0 || personList.isEmpty()) {
                            personArrayList.addAll(personList);
                            loadDataApi.loadDataPerson(personList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        **/

        Observable<List<Person>> listObservable = ApiService.getApiInterface().getPerson()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<DetailPerson> detailPersonObservable = ApiService.getApiInterface().getDeail("roland")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable.zip(listObservable, detailPersonObservable,
                new BiFunction<List<Person>, DetailPerson, UserAndDetail>() {
                    @Override
                    public UserAndDetail apply(List<Person> personList, DetailPerson detailPerson) throws Exception {
                        return new UserAndDetail(personList, detailPerson);
                    }
                }).subscribe(new Observer<UserAndDetail>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserAndDetail userAndDetail) {
                        Log.d(TAG, "onNext: "+userAndDetail.getPersonList().size());
                        personArrayList.addAll(userAndDetail.getPersonList());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        loadDataApi.loadDataPerson(personArrayList);
                    }
                });
//                .subscribe(data -> {
//                            Log.d("test", data.toString());
//                        }, throwable -> {
//
//                        }, () -> {
//
//                        }
//
//                );
    }
}
