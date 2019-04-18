package com.contactsmanager.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.contactsmanager.myapplication.model.User;
import com.contactsmanager.myapplication.model.UserDetail;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity {
    private String url;
    private CircleImageView iv_avatar;
    private TextView tv_name, tv_idUer;
    private Realm realm;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm = Realm.getDefaultInstance();
        url = getIntent().getStringExtra("url");
        login = getIntent().getStringExtra("login");

        init();
        showDetail(url);
        view();

    }

    private void init() {
        iv_avatar = findViewById(R.id.iv_avatar);
        tv_name = findViewById(R.id.tv_name);
        tv_idUer = findViewById(R.id.tv_idUser);


    }

    public void showDetail(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    realm.beginTransaction();

                    JSONObject jsonObject = new JSONObject(response);
                    final String login = jsonObject.getString("login");
                    final String avatar = jsonObject.getString("avatar_url");
                    final int id = jsonObject.getInt("id");
                    UserDetail detail = realm.where(UserDetail.class).equalTo("login", login).findFirst();
                    if (detail == null) {
                        UserDetail userDetail = realm.createObject(UserDetail.class);
                        userDetail.setId(id);
                        userDetail.setLogin(login);
                        userDetail.setAvatar(avatar);
                    }
                    realm.commitTransaction();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ;


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);



    }

    public void view(){
        realm.beginTransaction();
        RealmResults<UserDetail> results = realm.where(UserDetail.class).equalTo("login", login).findAll();
        for (UserDetail userDetail : results) {
            Glide.with(DetailActivity.this).load(userDetail.getAvatar()).into(iv_avatar);
            tv_idUer.setText(userDetail.getId() + "");
            tv_name.setText(userDetail.getLogin());
        }
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
