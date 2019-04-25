package com.nguyenminh.loginapi.presenter;

import com.nguyenminh.loginapi.model.SaveData;
import com.nguyenminh.loginapi.model.entity.UserFacebook;
import com.nguyenminh.loginapi.model.entity.UserGoogle;
import com.nguyenminh.loginapi.ui.MainView;

public class LoginPresenterCom implements LoginPresenter, PresenterListener {

    private MainView mainView;
    private SaveData saveData;

    public LoginPresenterCom(MainView mainView) {
        this.mainView = mainView;
        saveData = new SaveData(this);
    }

    @Override
    public void doLogin(UserGoogle userGoogle) {
        int code = saveData.checkSaveData(userGoogle);
        if(code == 1){
            mainView.isLogin(true);
        }
        else if(code == 0){
            mainView.isLogin(false);
        }
    }

    @Override
    public void doLoginFace(UserFacebook userFacebook) {
        int code = saveData.checkSaveDataFace(userFacebook);
        if(code == 1){
            mainView.isLogin(true);
        }
        else if(code == 0){
            mainView.isLogin(false);
        }
    }

    @Override
    public void onSuccess() {

    }
}
