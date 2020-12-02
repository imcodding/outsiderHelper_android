package com.mia.outsiderhelper.main.fragment.board;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.outsiderhelper.models.BoardBody;

import java.util.ArrayList;

import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class BoardService {
    private final BoardFragmentView boardFragmentView;

    public BoardService(BoardFragmentView boardFragmentView) {
        this.boardFragmentView = boardFragmentView;
    }

    void getBoardList() {
        getDatabaseReference().child("boards").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                boardFragmentView.getBoardListFailure(error.getMessage());
            }
        });
    }
}
