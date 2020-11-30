package com.mia.outsiderhelper.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.outsiderhelper.models.LoginResponse;

import static com.mia.outsiderhelper.ApplicationClass.FAILURE_CODE;
import static com.mia.outsiderhelper.ApplicationClass.SUCCESS_CODE;
import static com.mia.outsiderhelper.ApplicationClass.USER_ID;
import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class LoginService {
    private final LoginActivityView loginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    public void getUser(String userId, String hash) {
        getDatabaseReference().child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LoginResponse response = snapshot.getValue(LoginResponse.class);
                if(hash.equals(response.getHash())) {
                    USER_ID = response.getUserId();
                    loginActivityView.getUserSuccess(SUCCESS_CODE, response);
                } else {
                    loginActivityView.getUserSuccess(FAILURE_CODE, null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loginActivityView.getUserFailure(error.getMessage());
            }
        });
    }
}
