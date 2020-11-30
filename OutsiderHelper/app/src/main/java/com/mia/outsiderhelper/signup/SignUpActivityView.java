package com.mia.outsiderhelper.signup;

public interface SignUpActivityView {
    void checkUserIdSuccess();
    void checkUserIdFailure(String message);
    void postSignUpSuccess(int code);
    void postSignUpFailure(String message);
}
