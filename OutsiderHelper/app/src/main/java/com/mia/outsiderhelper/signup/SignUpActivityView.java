package com.mia.outsiderhelper.signup;

public interface SignUpActivityView {
    void postSignUpSuccess(int code);
    void postSignUpFailure(String message);
}
