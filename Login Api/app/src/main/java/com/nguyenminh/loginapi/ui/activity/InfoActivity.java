package com.nguyenminh.loginapi.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.nguyenminh.loginapi.R;
import com.nguyenminh.loginapi.model.entity.UserFacebook;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class InfoActivity extends AppCompatActivity {
    private Realm realm;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);
        RealmResults<UserFacebook> userFacebooks = realm.where(UserFacebook.class).findAll();
        Log.d("eee", "execute: "+userFacebooks.size());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
