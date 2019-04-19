package com.contactsmanager.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.contactsmanager.retrofit.R;
import com.contactsmanager.retrofit.api.ApiInterface;
import com.contactsmanager.retrofit.api.ApiService;
import com.contactsmanager.retrofit.model.UserDetail;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private String login;
    private Realm realm;
    private ApiInterface apiInterface;
    private CircleImageView iv_avatar;
    private TextView tv_name, tv_idUer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        apiInterface = ApiService.getApiInterface();
        realm = Realm.getDefaultInstance();

        login = getIntent().getStringExtra("login");

        init();
        showDetailUser(login);
    }

    private void init() {
        iv_avatar = findViewById(R.id.iv_avatar);
        tv_name = findViewById(R.id.tv_name);
        tv_idUer = findViewById(R.id.tv_idUser);

    }

    public void showDetailUser(final String login) {

        apiInterface.getUserDetail(login).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                if (response.body() != null){

                    UserDetail detail = realm.where(UserDetail.class).equalTo("login", login).findFirst();

                    if(detail == null){
                        realm.beginTransaction();

                        UserDetail userDetail = realm.createObject(UserDetail.class);
                        userDetail.setId(response.body().getId());
                        userDetail.setAvatar(response.body().getAvatar());
                        userDetail.setLogin(response.body().getLogin());

                        realm.commitTransaction();
                        showDetail();
                    } else {
                        showDetail();
                    }
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                showDetail();
            }
        });
    }

    public void showDetail(){
        UserDetail userDetail = realm.where(UserDetail.class).equalTo("login",login).findFirst();
        if(userDetail != null) {
            Glide.with(DetailActivity.this).load(userDetail.getAvatar()).into(iv_avatar);
            tv_idUer.setText(userDetail.getId() + "");
            tv_name.setText(userDetail.getLogin());
        }
        else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
