package com.mia.outsiderhelper.login;

public interface LoginActivityView {
    void getUserSuccess(int code);
    void getUserFailure(String message);
}
