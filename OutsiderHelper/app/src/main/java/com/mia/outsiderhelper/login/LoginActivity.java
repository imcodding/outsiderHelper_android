package com.mia.outsiderhelper.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.MainActivity;
import com.mia.outsiderhelper.signup.SignUpActivity;
import com.mia.outsiderhelper.util.HashUtil;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.mia.outsiderhelper.ApplicationClass.SUCCESS_CODE;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private EditText mEditId;
    private EditText mEditPw;
    private LoginService mLoginService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginService = new LoginService(this);

        mEditId = findViewById(R.id.login_edit_id);
        mEditPw = findViewById(R.id.login_edit_pw);
        mProgressBar = findViewById(R.id.progress_bar);

        mEditPw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int action, KeyEvent event) {
                if (action == EditorInfo.IME_ACTION_DONE || action == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    try {
                        login();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    public void onClickView(View v) throws NoSuchAlgorithmException {
        switch (v.getId()) {
            case R.id.login_btn_ok:
                login();
                break;
            case R.id.login_btn_sign_up:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() throws NoSuchAlgorithmException {
        String userId = mEditId.getText().toString();
        String password = mEditPw.getText().toString();

        userId = "test";
        password = "1234";
        if(!checkValidation(userId, password)) return;

        showProgressDialog();
        mLoginService.getUser(userId, HashUtil.sha256(password));
    }

    private boolean checkValidation(String userId, String password) {
        if(userId.length() == 0) {
            showCustomToast(getString(R.string.input_id_message));
            return false;
        } else if(password.length() == 0) {
            showCustomToast(getString(R.string.input_pw_message));
            return false;
        }
        return true;
    }

    @Override
    public void getUserSuccess(int code) {
        hideProgressDialog();
        if(code == SUCCESS_CODE) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            showCustomToast(getString(R.string.login_pw_no_match));
        }

    }

    @Override
    public void getUserFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.login_id_no_match));
    }
}
