package com.nguyenminh.googleapi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nguyenminh.googleapi.R;
import com.nguyenminh.googleapi.model.UserFacebook;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class InfoActivity extends AppCompatActivity {
    @BindView(R.id.tv_id)
    TextView tv_id;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        RealmResults<UserFacebook> userFacebooks = realm.where(UserFacebook.class).findAll();
        String a = getIntent().getStringExtra("name");
        Log.d("error", ""+a + "  "+ userFacebooks.size());
        tv_id.setText(a);
    }
}
