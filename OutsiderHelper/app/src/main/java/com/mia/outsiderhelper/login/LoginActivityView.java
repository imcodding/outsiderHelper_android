package com.mia.outsiderhelper.login;

import com.mia.outsiderhelper.models.LoginResponse;

public interface LoginActivityView {
    void getUserSuccess(int code, LoginResponse user);
    void getUserFailure(String message);
}
