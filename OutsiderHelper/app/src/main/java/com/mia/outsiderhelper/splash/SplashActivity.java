package com.mia.outsiderhelper.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.util.SharedPreferenceUtil;
import com.mia.outsiderhelper.login.LoginActivity;
import com.mia.outsiderhelper.main.MainActivity;
import com.mia.outsiderhelper.models.LoginResponse;

import static com.mia.outsiderhelper.ApplicationClass.USER_ID;

public class SplashActivity extends BaseActivity implements SplashActivityView {
    private SplashService mSplashService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashService = new SplashService(this);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!SharedPreferenceUtil.getBoolean("AUTO_LOGIN")) {
                    requestLogin();
                    return;
                }
                String userId = SharedPreferenceUtil.getString("USER_ID");
                if (userId == null) { requestLogin(); }
                else { mSplashService.autoLogin(userId); }
            }
        }, 1000);
    }

    @Override
    public void autoLoginSuccess(LoginResponse response) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", response);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void autoLoginFailure(String message) {
        showCustomToast(getString(R.string.login_auto_fail_message));
        requestLogin();
    }

    private void requestLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
