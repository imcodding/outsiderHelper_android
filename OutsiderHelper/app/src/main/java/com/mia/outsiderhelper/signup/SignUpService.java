package com.mia.outsiderhelper.signup;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.outsiderhelper.models.LoginResponse;

import java.util.HashMap;

import static com.mia.outsiderhelper.ApplicationClass.FAILURE_CODE;
import static com.mia.outsiderhelper.ApplicationClass.SUCCESS_CODE;
import static com.mia.outsiderhelper.ApplicationClass.USER_ID;
import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class SignUpService {
    private final SignUpActivityView signUpActivityView;

    public SignUpService(SignUpActivityView signUpActivityView) {
        this.signUpActivityView = signUpActivityView;
    }

    /**
     * ID 중복검사
     */
    void checkUserId(String userId) {
        getDatabaseReference().child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null) {
                    signUpActivityView.checkUserIdSuccess(FAILURE_CODE);
                    return;
                }
                signUpActivityView.checkUserIdSuccess(SUCCESS_CODE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                signUpActivityView.checkUserIdFailure(error.getMessage());
            }
        });
    }

    /**
     * 회원가입
     */
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
