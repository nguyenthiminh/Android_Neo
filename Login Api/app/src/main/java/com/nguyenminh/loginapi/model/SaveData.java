package com.nguyenminh.loginapi.model;

import android.util.Log;

import com.nguyenminh.loginapi.model.entity.UserFacebook;
import com.nguyenminh.loginapi.model.entity.UserGoogle;
import com.nguyenminh.loginapi.presenter.PresenterListener;

import io.realm.Realm;
import io.realm.RealmResults;

public class SaveData {
    private PresenterListener presenterListener;

    public SaveData(PresenterListener presenter) {
        this.presenterListener = presenter;
    }

    public int checkSaveData(final UserGoogle userGoogle) {
        Realm realm = null;
        final int[] code = {0};
        try {
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(userGoogle);
                }
            });
            RealmResults<UserGoogle> userGoogles = realm.where(UserGoogle.class).findAll();
            Log.d("user", "execute: "+userGoogles.size());

        } finally {
            if (realm != null) {
                realm.close();
            }
        }


        return code[0];
    }

    public int checkSaveDataFace(final UserFacebook userFacebook) {
        Log.d("abc", "checkSaveDataFace: "+userFacebook.getId()+ "  "+userFacebook.getName());
        Realm realm = null;
        final int[] code = {0};
        try {
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(userFacebook);
                }
            });
            RealmResults<UserFacebook> userFacebooks = realm.where(UserFacebook.class).findAll();
            Log.d("face", "execute: "+userFacebooks.size());

        } finally {
            if (realm != null) {
                realm.close();
            }
        }

        return code[0];
    }

}
