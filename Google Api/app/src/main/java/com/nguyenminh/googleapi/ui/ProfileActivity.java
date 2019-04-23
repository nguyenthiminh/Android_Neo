package com.nguyenminh.googleapi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.nguyenminh.googleapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.tv_name) TextView tv_name;

    @BindView(R.id.iv_avatar) CircleImageView iv_avatar;

    @BindView(R.id.tv_email) TextView tv_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        GoogleSignInAccount account = getIntent().getParcelableExtra("user");
        Glide.with(getApplicationContext()).load(account.getPhotoUrl()).into(iv_avatar);
        tv_name.setText(account.getDisplayName());
        tv_email.setText(account.getEmail());
    }
}
