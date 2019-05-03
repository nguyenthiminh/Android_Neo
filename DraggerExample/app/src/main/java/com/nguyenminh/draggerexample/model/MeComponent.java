package com.nguyenminh.draggerexample.model;

import com.nguyenminh.draggerexample.MainActivity;
import com.nguyenminh.draggerexample.model.entiy.Me;
import com.nguyenminh.draggerexample.model.entiy.MeModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MeModule.class})
public interface MeComponent {
    Me provideMe();

    void inject(MainActivity mainActivity);
}
