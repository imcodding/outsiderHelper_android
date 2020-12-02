package com.mia.outsiderhelper.main.fragment.board.write;

public interface BoardWriteActivityView {
    void getBoardNoLastSuccess(int lastBoardNo);
    void getBoardNoLastFailure(String message);

    void updateBoardNoLastSuccess();
    void updateBoardNoLastFailure(String message);

    void postWriteSuccess();
    void postWriteFailure(String message);
}
