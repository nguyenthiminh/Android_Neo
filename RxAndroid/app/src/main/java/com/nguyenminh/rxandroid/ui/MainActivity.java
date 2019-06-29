package com.nguyenminh.rxandroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nguyenminh.rxandroid.R;
import com.nguyenminh.rxandroid.model.entity.DetailPerson;
import com.nguyenminh.rxandroid.model.entity.Person;
import com.nguyenminh.rxandroid.presenter.MainPresenter;
import com.nguyenminh.rxandroid.ui.adapter.RecyclerAdapter;
import com.nguyenminh.rxandroid.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {
    private RecyclerAdapter adapter;
    @BindView(R.id.rcv) RecyclerView rcv;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter();
        presenter.attachView(this);
        init();
        presenter.getList();

    }

    private void init() {
        adapter = new RecyclerAdapter(this, presenter.personList);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(adapter);
    }

    @Override
    public void showList(List<Person> personList) {
        presenter.personList.clear();
        presenter.personList.addAll(personList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayResult(DetailPerson person) {

    }

    @Override
    public void displayError(String error) {

    }
}
