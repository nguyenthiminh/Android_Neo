package com.nguyenminh.loginapi.model;

import com.nguyenminh.loginapi.model.entity.UserFacebook;
import com.nguyenminh.loginapi.model.entity.UserGoogle;

public interface IUserGoogle {
    int checkSaveData(UserGoogle userGoogle);
    int checkSaveDataFace(UserFacebook userFacebook);
}
