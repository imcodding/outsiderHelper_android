package com.mia.outsiderhelper.main.fragment.board.detail.interfaces;

import com.mia.outsiderhelper.models.CommentBody;

import java.util.ArrayList;

public interface BoardDetailFragmentView {
    void getCommentsSuccess(ArrayList<CommentBody> comments);
    void getCommentsFailure(String message);

    void postCommentSuccess();
    void postCommentFailure(String message);

    void getCommentNoLastSuccess(int lastCommentNo);
    void getCommentNoLastFailure(String message);
}
