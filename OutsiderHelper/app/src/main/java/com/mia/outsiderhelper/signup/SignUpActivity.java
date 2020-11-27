package com.mia.outsiderhelper.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.models.SignUpBody;
import com.mia.outsiderhelper.util.HashUtil;

import java.util.Map;


public class SignUpActivity extends BaseActivity {

    private static final String TAG = "SignActivity";
    private DatabaseReference mPostReference;

    private AppCompatEditText mEditId;
    private AppCompatEditText mEditPw;
    private AppCompatEditText mEditRePw;
    private AppCompatEditText mEditName;
    private AppCompatEditText mEditAge;
    private AppCompatEditText mEditNickname;
    private AppCompatEditText mEditUniversity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mPostReference = FirebaseDatabase.getInstance().getReference();

        mEditId = findViewById(R.id.sign_up_edit_id);
        mEditPw = findViewById(R.id.sign_up_edit_pw);
        mEditRePw = findViewById(R.id.sign_up_edit_pw_check);
        mEditName = findViewById(R.id.sign_up_edit_name);
        mEditAge = findViewById(R.id.sign_up_edit_age);
        mEditNickname = findViewById(R.id.sign_up_edit_nickname);
//        mEditUniversity = findViewById(R.id.sign_up_edit_university);
    }

    public void onSignUp(View view) {
        String userId = mEditId.getText().toString();
        String password = mEditPw.getText().toString();
        String rePassword = mEditRePw.getText().toString();
        String name = mEditName.getText().toString();
        String nickname = mEditNickname.getText().toString();
        String age = mEditAge.getText().toString();
        String hash = null;

        if (!checkValidation(userId, password, rePassword, name, nickname, age)) return;
        try {
            hash = HashUtil.encryptAES256(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        postFirebaseDatabase(userId, hash, name, nickname, age);
    }

    private boolean checkValidation(String id, String pw, String rePw, String name, String nickname, String age) {
        if (id.length() == 0) {
            showCustomToast(getString(R.string.input_id_message));
            return false;
        } else if (pw.length() == 0) {
            showCustomToast(getString(R.string.input_pw_message));
            return false;
        } else if (rePw.length() == 0) {
            showCustomToast(getString(R.string.input_pw_check_message));
            return false;
        } else if (name.length() == 0) {
            showCustomToast(getString(R.string.input_name_message));
            return false;
        } else if (nickname.length() == 0) {
            showCustomToast(getString(R.string.input_nickname_message));
            return false;
        } else if (age.length() == 0) {
            showCustomToast(getString(R.string.input_age_message));
            return false;
        }
//        else if (university.equals("")) {
//            showCustomToast(getString(R.string.input_id_message));
//            return false;
//        }

        if (!pw.equals(rePw)) {
            showCustomToast(getString(R.string.input_pw_noMatch_message));
            return false;
        }
        return true;
    }


    public void postFirebaseDatabase(String userId, String hash, String name, String nickname, String age) {
        Map<String, Object> postValues = null;
        SignUpBody post = new SignUpBody(userId, hash, name, nickname, age);
        postValues = post.toMap();

        mPostReference.child("users").child(userId).setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "SinUp Success!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "SinUp Fail!");
                    }
                });
    }

}
