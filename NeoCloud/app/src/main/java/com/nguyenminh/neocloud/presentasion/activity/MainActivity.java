package com.nguyenminh.neocloud.presentasion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nguyenminh.neocloud.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_salary_advance);
        ButterKnife.bind(this);
    }

}
