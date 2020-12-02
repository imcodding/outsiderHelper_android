package com.mia.outsiderhelper.main.fragment.board;

import com.mia.outsiderhelper.models.BoardBody;

import java.util.ArrayList;

public interface BoardFragmentView {
    void getBoardListSuccess(ArrayList<BoardBody> boards);
    void getBoardListFailure(String message);
}
