package com.contactsmanager.retrofit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.contactsmanager.retrofit.R;
import com.contactsmanager.retrofit.adapter.UserListAdapter;
import com.contactsmanager.retrofit.api.ApiInterface;
import com.contactsmanager.retrofit.api.ApiService;
import com.contactsmanager.retrofit.model.User;
import com.contactsmanager.retrofit.model.UserDetail;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserListAdapter.Listener {

    private UserListAdapter mAdapter;
    private RecyclerView rcv_user;
    private ApiInterface mApiInterface;
    private List<User> userList = new ArrayList<>();
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiInterface = ApiService.getApiInterface();
        realm = Realm.getDefaultInstance();
        init();
        loadUsers();
    }

    private void init() {
        rcv_user = findViewById(R.id.rcv_user);

        mAdapter = new UserListAdapter(MainActivity.this, userList, this);
        rcv_user.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        rcv_user.setAdapter(mAdapter);
    }

    @Override
    public void onClickItemUser(String login) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    private void loadUsers() {
        mApiInterface.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.body().size() != 0) {
                    realm.beginTransaction();
                    RealmResults<User> users = realm.where(User.class).findAll();

                    if (users.size() == 0) {
                        for (int i = 0; i < response.body().size(); i++) {
                            User user = new User();
                            user.setId(response.body().get(i).getId());
                            user.setAvatar(response.body().get(i).getAvatar());
                            user.setLogin(response.body().get(i).getLogin());
                            user.setUrl(response.body().get(i).getUrl());
                            realm.insertOrUpdate(user);
                        }

                        showListUser();
                    } else {
                        showListUser();
                    }
                    realm.commitTransaction();
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                showListUser();
            }
        });
    }

    public void showListUser() {
        userList.clear();
        RealmResults<User> results = realm.where(User.class).findAll();
        if (results.size() != 0) {
            userList.addAll(results);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
