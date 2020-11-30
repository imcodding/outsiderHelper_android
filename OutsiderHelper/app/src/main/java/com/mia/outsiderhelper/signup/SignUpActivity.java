package com.mia.outsiderhelper.signup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.models.SignUpBody;
import com.mia.outsiderhelper.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private static final String TAG = "SignActivity";

    private SignUpService mSignUpService;
    private AppCompatEditText mEditId;
    private AppCompatEditText mEditPw;
    private AppCompatEditText mEditRePw;
    private AppCompatEditText mEditName;
    private AppCompatEditText mEditAge;
    private AppCompatEditText mEditNickname;
    private AppCompatEditText mEditUniversity;
    private boolean isCheckUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mSignUpService = new SignUpService(this);

        mEditId = findViewById(R.id.sign_up_edit_id);
        mEditPw = findViewById(R.id.sign_up_edit_pw);
        mEditRePw = findViewById(R.id.sign_up_edit_pw_check);
        mEditName = findViewById(R.id.sign_up_edit_name);
        mEditAge = findViewById(R.id.sign_up_edit_age);
        mEditNickname = findViewById(R.id.sign_up_edit_nickname);
        mProgressBar = findViewById(R.id.progress_bar);
        mEditUniversity = findViewById(R.id.sign_up_edit_university);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickView(View view) throws NoSuchAlgorithmException {
        switch (view.getId()) {
            case R.id.sign_up_btn_check_id:
                String ckUserId = mEditId.getText().toString();
                showProgressDialog();
                mSignUpService.checkUserId(ckUserId);
                break;
            case R.id.sign_up_iv_ok:
                String userId = mEditId.getText().toString();
                String password = mEditPw.getText().toString();
                String rePassword = mEditRePw.getText().toString();
                String name = mEditName.getText().toString();
                String nickname = mEditNickname.getText().toString();
                String age = mEditAge.getText().toString();
                String university = mEditUniversity.getText().toString();

                if (!checkValidation(userId, password, rePassword, name, nickname, age, university)) return;

                String hash = HashUtil.sha256(password);

                SignUpBody post = new SignUpBody(userId, hash, name, nickname, age, university);
                HashMap<String, Object> postValues = post.toMap();

                showProgressDialog();
                mSignUpService.postSignUp(userId, postValues);
                break;
            case R.id.sign_up_iv_back:
                finish();
                break;
        }
    }

    @Override
    public void checkUserIdSuccess() {
        hideProgressDialog();
        showCustomToast(getString(R.string.sign_up_check_id_ok));
        isCheckUserId = true;
    }

    @Override
    public void checkUserIdFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.sign_up_check_id_no));
    }

    @Override
    public void postSignUpSuccess(int code) {
        hideProgressDialog();
        showCustomToast(getString(R.string.sign_up_success));
        finish();
    }

    @Override
    public void postSignUpFailure(String message) {
        Log.d(TAG, message);
        showCustomToast(getString(R.string.sign_up_failure));
        isCheckUserId = false;
    }

    private boolean checkValidation(String id, String pw, String rePw, String name, String nickname, String age, String university) {
        if (!isCheckUserId) {
            showCustomToast(getString(R.string.sign_up_check_id));
            return false;
        } else if (id.length() == 0) {
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
        } else if (university.length() == 0) {
            showCustomToast(getString(R.string.input_university_message));
            return false;
        }

        if (!pw.equals(rePw)) {
            showCustomToast(getString(R.string.input_pw_noMatch_message));
            return false;
        }
        return true;
    }
}
