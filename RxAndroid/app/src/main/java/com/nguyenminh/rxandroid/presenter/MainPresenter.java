package com.nguyenminh.rxandroid.presenter;

import android.util.Log;
import android.widget.SearchView;

import com.nguyenminh.rxandroid.api.ApiService;
import com.nguyenminh.rxandroid.model.Data;
import com.nguyenminh.rxandroid.model.LoadDataApi;
import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;
import com.nguyenminh.rxandroid.ui.view.MainView;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static android.support.constraint.Constraints.TAG;

public class MainPresenter implements LoadDataApi, BasePresenter<MainView>, SearchPresenterInterface {
    private MainView mainView;
    private Data data;
    public List<Person> personList = new ArrayList<>();

    public MainPresenter(){
        data = new Data(this);
    }

    @Override
    public void attachView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }

    public void getList(){
        data.getListPerson();
    }

    @Override
    public void loadDataPerson(List<Person> personList) {
        mainView.showList(personList);
    }

    @Override
    public void loadDataDetailPerson(DetailPerson detailPerson) {

    }

    @Override
    public void getResult(SearchView searchView) {
        getObservableQuery(searchView)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        if(s.equals("")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                })
                .debounce(2, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<DetailPerson>>() {
                    @Override
                    public Observable<DetailPerson> apply(@NonNull String s) throws Exception {
                        return ApiService.getApiInterface().getDeail(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());
    }

    private Observable<String> getObservableQuery(SearchView searchView){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;
    }

    public DisposableObserver<DetailPerson> getObserver(){
        return new DisposableObserver<DetailPerson>() {

            @Override
            public void onNext(@NonNull DetailPerson detailPerson) {
                mainView.displayResult(detailPerson);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                mainView.displayError("Error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
