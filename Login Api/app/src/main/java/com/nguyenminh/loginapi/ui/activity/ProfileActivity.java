package com.nguyenminh.loginapi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.nguyenminh.loginapi.R;
import com.nguyenminh.loginapi.model.entity.UserGoogle;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.iv_avatar) CircleImageView iv_avatar;

    @BindView(R.id.tv_email) TextView tv_email;

    @BindView(R.id.tv_name) TextView tv_name;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        initView();

    }

    private void initView() {
        GoogleSignInAccount account = getIntent().getParcelableExtra("user");
        Glide.with(getApplicationContext()).load(account.getPhotoUrl()).into(iv_avatar);
        tv_name.setText(account.getDisplayName());
        tv_email.setText(account.getEmail());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
