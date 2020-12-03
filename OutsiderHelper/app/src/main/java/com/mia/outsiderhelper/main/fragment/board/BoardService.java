package com.mia.outsiderhelper.main.fragment.board;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.mia.outsiderhelper.models.BoardBody;

import java.util.ArrayList;
import java.util.HashMap;

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
                HashMap map = (HashMap) snapshot.getValue();
                if (map == null) {
                    boardFragmentView.getBoardListFailure(null);
                    return;
                }
                ArrayList<BoardBody> boards = new ArrayList<>();
                for (int i = 1; i < map.size(); i++) {
                    HashMap<String, Object> item = (HashMap<String, Object>) map.get(String.valueOf(i));
                    boards.add(new BoardBody().toObject(item));
                }
                boardFragmentView.getBoardListSuccess(boards);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                boardFragmentView.getBoardListFailure(error.getMessage());
            }
        });
    }
}
