package com.mia.outsiderhelper.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.MainActivity;
import com.mia.outsiderhelper.signup.SignUpActivity;

public class LoginActivity extends BaseActivity {

    private EditText mEditId;
    private EditText mEditPw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditId = findViewById(R.id.login_edit_id);
        mEditPw = findViewById(R.id.login_edit_pw);
    }


    @SuppressLint("NonConstantResourceId")
    public void onClickView(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.login_btn_ok:
                String userId = mEditId.getText().toString();
                String password = mEditId.getText().toString();
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.login_btn_sign_up:
                intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
