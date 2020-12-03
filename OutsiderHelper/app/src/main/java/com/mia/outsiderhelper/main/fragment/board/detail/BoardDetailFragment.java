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
import com.mia.outsiderhelper.models.BoardBody;

public class BoardDetailFragment extends BaseFragment {

    private TextView mBoardTitle;
    private TextView mBoardContent;
    private TextView mBoardDate;
    private EditText mCommentContent;
    private Button mCommentPostBtn;

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

        mBoardTitle = view.findViewById(R.id.bd_detail_tv_title);
        mBoardContent = view.findViewById(R.id.bd_detail_tv_content);
        mBoardDate = view.findViewById(R.id.bd_detail_tv_date);
        mCommentContent = view.findViewById(R.id.bd_detail_edit_comment_cont);
        mCommentPostBtn = view.findViewById(R.id.bd_detail_btn_comment_post);
        mCommentPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setViewValue();

        return view;
    }

    private void setViewValue() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            BoardBody board = (BoardBody) bundle.getSerializable("board");
            mBoardTitle.setText(board.getTitle());
            mBoardContent.setText(board.getContent());
            mBoardDate.setText(board.getDate());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
