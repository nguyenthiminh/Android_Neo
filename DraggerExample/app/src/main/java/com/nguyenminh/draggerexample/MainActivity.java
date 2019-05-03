package com.nguyenminh.draggerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nguyenminh.draggerexample.model.DaggerMeComponent;
import com.nguyenminh.draggerexample.model.MeComponent;
import com.nguyenminh.draggerexample.model.entiy.Friend;
import com.nguyenminh.draggerexample.model.entiy.Me;
import com.nguyenminh.draggerexample.model.entiy.MeModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    Me me;
    @Inject
    Friend friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeComponent meComponent = DaggerMeComponent.builder().meModule(new MeModule()).build();

    me = meComponent.provideMe();
}
}
