package com.mia.outsiderhelper.main.fragment.board.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.board.detail.interfaces.BoardDetailFragmentView;
import com.mia.outsiderhelper.models.BoardBody;
import com.mia.outsiderhelper.models.CommentBody;

import java.util.ArrayList;

import static com.mia.outsiderhelper.ApplicationClass.USER_ID;

public class BoardDetailFragment extends BaseFragment implements BoardDetailFragmentView {

    private BoardDetailService mBoardDetailService;
    
    private TextView mBoardTitle;
    private TextView mBoardContent;
    private TextView mBoardDate;
    private EditText mCommentContent;

    private int mBoardNo;
    private int mCommentNo;

    public static BoardDetailFragment newInstance(BoardBody board) {
        Bundle args = new Bundle();
        args.putSerializable("board", board);
        BoardDetailFragment fragment = new BoardDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_detail, container, false);

        mBoardDetailService = new BoardDetailService(this);

        mProgressBar = view.findViewById(R.id.progress_bar);
        mBoardTitle = view.findViewById(R.id.bd_detail_tv_title);
        mBoardContent = view.findViewById(R.id.bd_detail_tv_content);
        mBoardDate = view.findViewById(R.id.bd_detail_tv_date);
        mCommentContent = view.findViewById(R.id.bd_detail_edit_comment_cont);

        Button commentPostBtn = view.findViewById(R.id.bd_detail_btn_comment_post);
        commentPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = String.valueOf(mCommentContent.getText());

                if(!checkValidation(commentContent)) return;

                showProgressDialog();
                mBoardDetailService.getCommentNoLast(mBoardNo);
            }
        });

        setViewValue();

        return view;
    }

    private boolean checkValidation(String commentContent) {
        if(commentContent.length() == 0) {
            showCustomToast(getString(R.string.board_detail_input_comment));
            return false;
        }
        return true;
    }

    private void setViewValue() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            BoardBody board = (BoardBody) bundle.getSerializable("board");
            mBoardTitle.setText(board.getTitle());
            mBoardContent.setText(board.getContent());
            mBoardDate.setText(board.getDate());

            mBoardNo = board.getBoardNo();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void getCommentNoLastSuccess(int lastCommentNo) {
        if(lastCommentNo == 1) mCommentNo = lastCommentNo;
        else mCommentNo = lastCommentNo + 1;

        String commentContent = String.valueOf(mCommentContent.getText());

        CommentBody comment = new CommentBody(mCommentNo, commentContent, USER_ID);
        mBoardDetailService.postComment(mBoardNo, mCommentNo, comment.toMap());
    }

    @Override
    public void getCommentNoLastFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_not_working));
    }

    @Override
    public void getCommentsSuccess(ArrayList<CommentBody> comments) {
        hideProgressDialog();
    }

    @Override
    public void getCommentsFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_not_working));
    }

    @Override
    public void postCommentSuccess() {
        hideProgressDialog();
        showCustomToast(getString(R.string.board_detail_comment_success));
        mBoardDetailService.updateCommentNoLast(mBoardNo, mCommentNo);
    }

    @Override
    public void postCommentFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_not_working));
    }
}
