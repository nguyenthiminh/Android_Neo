package com.nguyenminh.loginapi.presenter;

import com.nguyenminh.loginapi.model.entity.UserFacebook;
import com.nguyenminh.loginapi.model.entity.UserGoogle;

public interface LoginPresenter {
    void doLogin(UserGoogle userGoogle);
    void doLoginFace(UserFacebook userFacebook);
}
