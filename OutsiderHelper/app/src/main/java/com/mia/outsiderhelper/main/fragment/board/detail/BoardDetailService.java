package com.mia.outsiderhelper.main.fragment.board.detail;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mia.outsiderhelper.main.fragment.board.detail.interfaces.BoardDetailFragmentView;
import com.mia.outsiderhelper.models.BoardBody;
import com.mia.outsiderhelper.models.CommentBody;

import java.util.ArrayList;
import java.util.HashMap;

import static com.mia.outsiderhelper.ApplicationClass.getDatabaseReference;

public class BoardDetailService {
    private final BoardDetailFragmentView boardDetailFragmentView;

    public BoardDetailService(BoardDetailFragmentView boardDetailFragmentView) {
        this.boardDetailFragmentView = boardDetailFragmentView;
    }

    void postComment(int boardNo, int commentNo, HashMap<String, Object> postValues) {
        String cNo = String.valueOf(commentNo);
        getDatabaseReference().child("comments").child("bNo_" + boardNo).child(cNo).setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        boardDetailFragmentView.postCommentSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        boardDetailFragmentView.postCommentFailure(e.getMessage());
                    }
                });
    }

    void getCommentNoLast(int boardNo) {
        getDatabaseReference().child("comments").child("bNo_"+boardNo).child("lastCommentNo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() == null) {
                    boardDetailFragmentView.getCommentNoLastSuccess(0);
                } else {
                    String lastCommentNo = String.valueOf(snapshot.getValue());
                    boardDetailFragmentView.getCommentNoLastSuccess(Integer.parseInt(lastCommentNo));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                boardDetailFragmentView.getCommentNoLastFailure(error.getMessage());
            }
        });
    }

    void updateCommentNoLast(int boardNo, int commentNo) {
        getDatabaseReference().child("comments").child("bNo_" + boardNo).child("lastCommentNo").setValue(commentNo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    void getComments(int boardNo) {
        getDatabaseReference().child("comments").child("bNo_" + boardNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap map = (HashMap) snapshot.getValue();
                if(map == null) {
                    boardDetailFragmentView.getCommentsFailure(null);
                    return;
                }

                ArrayList<CommentBody> comments = new ArrayList<>();
                for(int i = 1; i < map.size(); i++) {
                    HashMap<String, Object> item = (HashMap<String, Object>) map.get(String.valueOf(i));
                    comments.add(new CommentBody().toObject(item));
                }
                boardDetailFragmentView.getCommentsSuccess(comments);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                boardDetailFragmentView.getCommentsFailure(error.getMessage());
            }
        });
    }
}
