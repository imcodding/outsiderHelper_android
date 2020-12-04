package com.mia.outsiderhelper.main.fragment.board.write;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class BoardWriteService {
    private final BoardWriteActivityView boardWriteActivityView;

    public BoardWriteService(BoardWriteActivityView boardWriteActivityView) {
        this.boardWriteActivityView = boardWriteActivityView;
    }

    void getBoardNoLast() {
        getDatabaseReference().child("boards").child("lastBoardNo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() == null) {
                    boardWriteActivityView.getBoardNoLastSuccess(1);
                } else {
                    String lastBoardNo = snapshot.getValue().toString();
                    boardWriteActivityView.getBoardNoLastSuccess(Integer.parseInt(lastBoardNo));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void updateBoardNoLast(int lastBoardNo) {
        getDatabaseReference().child("boards").child("lastBoardNo").setValue(lastBoardNo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        boardWriteActivityView.updateBoardNoLastSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        boardWriteActivityView.updateBoardNoLastFailure(e.getMessage());
                    }
                });
    }

    void postWriting(int lastBoardNo, HashMap<String, Object> postValues) {
        String lastNo = String.valueOf(lastBoardNo);
        getDatabaseReference().child("boards").child(lastNo).setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        boardWriteActivityView.postWriteSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        boardWriteActivityView.postWriteFailure(e.getMessage());
                    }
                });
    }
}
