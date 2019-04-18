package com.contactsmanager.myapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.contactsmanager.myapplication.adapter.UserListAdapter;
import com.contactsmanager.myapplication.api.API;
import com.contactsmanager.myapplication.model.User;
import com.contactsmanager.myapplication.model.UserDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements UserListAdapter.Listener {
    private Realm realm;
    private RecyclerView rcv_user;
    private UserListAdapter adapter;
    private List<User> userList = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        init();
        getUser();
        showUser();
    }

    private void init() {
        rcv_user = findViewById(R.id.rcv_user);

        adapter = new UserListAdapter(MainActivity.this, userList, this);
        rcv_user.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        rcv_user.setAdapter(adapter);

    }

    private void getUser() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.GET,
                API.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    realm.beginTransaction();
                    User user = realm.where(User.class).findFirst();
                    if (user == null) {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                final String login = obj.getString("login");
                                final String avatar = obj.getString("avatar_url");
                                final String url = obj.getString("url");
                                final int id = obj.getInt("id");

                                User user1 = realm.createObject(User.class);
                                user1.setLogin(login);
                                user1.setId(id);
                                user1.setAvatar(avatar);
                                user1.setUrl(url);

                                UserDetail userDetail = getDetail(url);
                                UserDetail u = realm.copyToRealm(userDetail);
                                user1.getUserDetails().add(u);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    realm.commitTransaction();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);

    }

    public UserDetail getDetail(String url) {
        final UserDetail userDetail = new UserDetail();
        RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String login = jsonObject.getString("login");
                    String avatar = jsonObject.getString("avatar_url");
                    int id = jsonObject.getInt("id");


                    userDetail.setId(id);
                    userDetail.setAvatar(avatar);
                    userDetail.setLogin(login);
                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);
        return userDetail;
    }

    public void showUser() {
        userList.clear();
        RealmResults<User> results = realm.where(User.class).findAll();
        for (User user : results) {
            userList.add(user);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void onClickItemUser(String url, String login) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("abc", url);
        intent.putExtra("login", login);
        startActivity(intent);
    }
}
