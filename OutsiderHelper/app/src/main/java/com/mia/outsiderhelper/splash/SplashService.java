package com.mia.outsiderhelper.splash;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.outsiderhelper.models.LoginResponse;

import static com.mia.outsiderhelper.ApplicationClass.USER_ID;
import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class SplashService {
    private final SplashActivityView splashActivityView;

    public SplashService(SplashActivityView splashActivityView) {
        this.splashActivityView = splashActivityView;
    }

    void autoLogin(String userId) {
        getDatabaseReference().child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LoginResponse response = snapshot.getValue(LoginResponse.class);
                if(response == null) {
                    splashActivityView.autoLoginFailure(null);
                    return;
                }
                USER_ID = userId;
                splashActivityView.autoLoginSuccess(response);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                splashActivityView.autoLoginFailure(error.getMessage());
            }
        });
    }
}

