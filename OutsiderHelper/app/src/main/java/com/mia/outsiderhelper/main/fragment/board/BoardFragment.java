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
import com.mia.outsiderhelper.main.fragment.board.detail.BoardDetailFragment;
import com.mia.outsiderhelper.main.fragment.board.detail.interfaces.OnItemClickListener;
import com.mia.outsiderhelper.main.fragment.board.write.BoardWriteActivity;
import com.mia.outsiderhelper.models.BoardBody;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class BoardFragment extends BaseFragment implements BoardFragmentView, OnItemClickListener {

    private BoardListAdapter mBoardListAdapter;
    private RecyclerView mRvBoards;
    private BoardService mBoardService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        mBoardService = new BoardService(this);
        mBoardListAdapter = new BoardListAdapter();
        mBoardListAdapter.setListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvBoards = view.findViewById(R.id.recycler_board);
        mRvBoards.setLayoutManager(manager);
        mProgressBar = view.findViewById(R.id.progress_bar);

        AppCompatImageView boardIvWrite = view.findViewById(R.id.board_iv_write);
        boardIvWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();
        mBoardService.getBoardList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 101) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                BoardBody board = (BoardBody) bundle.get("board");
                mBoardListAdapter.addItem(board);
            }
        }
    }

    @Override
    public void getBoardListSuccess(ArrayList<BoardBody> boards) {
        hideProgressDialog();

        mBoardListAdapter.setBoards(boards);
        mRvBoards.setAdapter(mBoardListAdapter);
    }

    @Override
    public void getBoardListFailure(String message) {
        hideProgressDialog();
    }

    @Override
    public void onItemClick(int pos) {
        BoardBody board = mBoardListAdapter.getItem(pos);
        getFragmentManager().beginTransaction().replace(R.id.board_container, BoardDetailFragment.newInstance(board)).commit();
    }
}
