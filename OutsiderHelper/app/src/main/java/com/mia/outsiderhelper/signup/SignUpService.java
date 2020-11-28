package com.mia.outsiderhelper.signup;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

import static com.mia.outsiderhelper.ApplicationClass.SUCCESS_CODE;
import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class SignUpService {
    private final SignUpActivityView signUpActivityView;

    public SignUpService(SignUpActivityView signUpActivityView) {
        this.signUpActivityView = signUpActivityView;
    }

    void postSignUp(String userId, HashMap<String, Object> postValues) {
        getDatabaseReference().child("users").child(userId).setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        signUpActivityView.postSignUpSuccess(SUCCESS_CODE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        signUpActivityView.postSignUpFailure(e.getMessage());
                    }
                });
    }
}
