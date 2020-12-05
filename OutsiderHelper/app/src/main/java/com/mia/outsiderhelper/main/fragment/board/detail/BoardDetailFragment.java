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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.board.detail.comment.CommentListAdapter;
import com.mia.outsiderhelper.main.fragment.board.detail.interfaces.BoardDetailFragmentView;
import com.mia.outsiderhelper.models.BoardBody;
import com.mia.outsiderhelper.models.CommentBody;

import java.util.ArrayList;

import static com.mia.outsiderhelper.ApplicationClass.USER_ID;

public class BoardDetailFragment extends BaseFragment implements BoardDetailFragmentView {

    private BoardDetailService mBoardDetailService;
    private CommentListAdapter mCommentListAdapter;

    private TextView mBoardTvTitle;
    private TextView mBoardTvContent;
    private TextView mBoardTvDate;
    private EditText mCommentEditContent;
    private RecyclerView mRvComments;

    private int mBoardNo;
    private int mCommentNo;
    private CommentBody mCommentBody;

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
        mCommentListAdapter = new CommentListAdapter();

        mProgressBar = view.findViewById(R.id.progress_bar);
        mBoardTvTitle = view.findViewById(R.id.bd_detail_tv_title);
        mBoardTvContent = view.findViewById(R.id.bd_detail_tv_content);
        mBoardTvDate = view.findViewById(R.id.bd_detail_tv_date);
        mCommentEditContent = view.findViewById(R.id.bd_detail_edit_comment_cont);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvComments = view.findViewById(R.id.recycler_comment);
        mRvComments.setLayoutManager(manager);

        TextView commentPostBtn = view.findViewById(R.id.bd_detail_btn_comment_post);
        commentPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = String.valueOf(mCommentEditContent.getText());

                if(!checkValidation(commentContent)) return;

                showProgressDialog();
                mBoardDetailService.getCommentNoLast(mBoardNo);

                keyboardDown();
            }
        });

        setViewValue();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();
        mBoardDetailService.getComments(mBoardNo);
    }

    private void setViewValue() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            BoardBody board = (BoardBody) bundle.getSerializable("board");
            mBoardTvTitle.setText(board.getTitle());
            mBoardTvContent.setText(board.getContent());
            mBoardTvDate.setText(board.getDate());

            mBoardNo = board.getBoardNo();
        }
    }

    @Override
    public void getCommentsSuccess(ArrayList<CommentBody> comments) {
        hideProgressDialog();
        mCommentListAdapter.setComments(comments);
        mRvComments.setAdapter(mCommentListAdapter);
    }

    @Override
    public void getCommentsFailure(String message) {
        hideProgressDialog();
    }

    @Override
    public void getCommentNoLastSuccess(int lastCommentNo) {
        mCommentNo = lastCommentNo + 1;
        String commentContent = String.valueOf(mCommentEditContent.getText());
        mCommentBody = new CommentBody(mCommentNo, commentContent, USER_ID);
        mBoardDetailService.postComment(mBoardNo, mCommentNo, mCommentBody.toMap());
    }

    @Override
    public void getCommentNoLastFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_not_working));
    }

    @Override
    public void postCommentSuccess() {
        hideProgressDialog();
        showCustomToast(getString(R.string.board_detail_comment_success));
        mCommentListAdapter.addItem(mCommentBody);
        mCommentEditContent.setText(null);
        mBoardDetailService.updateCommentNoLast(mBoardNo, mCommentNo);
    }

    @Override
    public void postCommentFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_not_working));
    }

    private boolean checkValidation(String commentContent) {
        if(commentContent.length() == 0) {
            showCustomToast(getString(R.string.board_detail_input_comment));
            return false;
        }
        return true;
    }
}
