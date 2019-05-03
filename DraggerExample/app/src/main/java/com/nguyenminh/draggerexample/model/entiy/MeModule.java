package com.nguyenminh.draggerexample.model.entiy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MeModule {
    @Provides @Singleton
    Friend provideFriend(){
        return new Friend();
    }

    @Provides @Singleton
    Me prpvideMe(){
        return new Me(new Friend());
    }
}
