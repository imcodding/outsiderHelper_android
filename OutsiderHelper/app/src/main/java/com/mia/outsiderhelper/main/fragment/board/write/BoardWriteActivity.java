package com.mia.outsiderhelper.main.fragment.board.write;

import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.models.BoardBody;

import static com.mia.outsiderhelper.ApplicationClass.USER_ID;

public class BoardWriteActivity extends BaseActivity implements BoardWriteActivityView {

    private final static String TAG = "BoardWriteActivity";
    private BoardWriteService mBoardWriteService;
    private AppCompatEditText mBoardTitle;
    private AppCompatEditText mBoardContent;

    private int mBoardNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        mProgressBar = findViewById(R.id.progress_bar);
        mBoardTitle = findViewById(R.id.bd_write_edit_title);
        mBoardContent = findViewById(R.id.bd_write_edit_content);

        mBoardWriteService = new BoardWriteService(this);
    }


    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.bd_write_save:
                if(!checkValidation()) return;
                showProgressDialog();
                mBoardWriteService.getBoardNoLast();
                break;
            case R.id.bd_write_cancel:
                finish();
                break;
        }
    }

    @Override
    public void getBoardNoLastSuccess(int lastBoardNo) {
        if(lastBoardNo == 1) mBoardNo = lastBoardNo;
        else mBoardNo = lastBoardNo + 1;

        String title = String.valueOf(mBoardTitle.getText());
        String content = String.valueOf(mBoardContent.getText());

        BoardBody board = new BoardBody(mBoardNo, USER_ID, title, content);
        mBoardWriteService.postWriting(mBoardNo, board.toMap());
    }

    @Override
    public void getBoardNoLastFailure(String message) {
        Log.d(TAG, message);
    }

    @Override
    public void postWriteSuccess() {
        hideProgressDialog();
        mBoardWriteService.updateBoardNoLast(mBoardNo);
        finish();
    }

    @Override
    public void postWriteFailure(String message) {
        hideProgressDialog();
        Log.d(TAG, message);
    }

    @Override
    public void updateBoardNoLastSuccess() {

    }

    @Override
    public void updateBoardNoLastFailure(String message) {
        Log.d(TAG, message);
    }

    private boolean checkValidation() {
        String value = String.valueOf(mBoardTitle.getText());
        if(value.length() == 0) {
            showCustomToast(getString(R.string.board_write_input_title));
            return false;
        }
        value = String.valueOf(mBoardContent.getText());
        if(value.length() == 0) {
            showCustomToast(getString(R.string.board_write_input_content));
            return false;
        }
        return true;
    }
}