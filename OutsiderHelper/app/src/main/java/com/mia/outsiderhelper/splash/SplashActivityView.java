package com.mia.outsiderhelper.splash;

import com.mia.outsiderhelper.models.LoginResponse;

public interface SplashActivityView {
    void autoLoginSuccess(LoginResponse user);
    void autoLoginFailure(String message);
}
