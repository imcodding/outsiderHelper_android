package com.mia.outsiderhelper.main.fragment.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.board.write.BoardWriteActivity;
import com.mia.outsiderhelper.models.BoardBody;

import java.util.ArrayList;

public class BoardFragment extends BaseFragment implements BoardFragmentView, View.OnClickListener {

    private BoardListAdapter mBoardListAdapter;
    private RecyclerView rvBoards;
    private AppCompatImageView mBoardIvWrite;
    private BoardService mBoardService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        mBoardService = new BoardService(this);

        mProgressBar = view.findViewById(R.id.progress_bar);
        mBoardIvWrite = view.findViewById(R.id.board_iv_write);
        mBoardIvWrite.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvBoards = view.findViewById(R.id.recycler_board);
        rvBoards.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();
        mBoardService.getBoardList();
    }

    @Override
    public void onClick(View view) {
        Intent intent =  new Intent(getActivity(), BoardWriteActivity.class);
        startActivity(intent);
    }

    @Override
    public void getBoardListSuccess(ArrayList<BoardBody> boards) {
        hideProgressDialog();

        mBoardListAdapter = new BoardListAdapter(boards);
        rvBoards.setAdapter(mBoardListAdapter);
    }

    @Override
    public void getBoardListFailure(String message) {
        hideProgressDialog();
    }
}
