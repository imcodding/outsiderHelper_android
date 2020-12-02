package com.mia.outsiderhelper.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import static com.mia.outsiderhelper.ApplicationClass.DATE_FORMAT;


public class BoardBody implements Serializable {
    private int boardNo;
    private String userId;
    private String title;
    private String content;
    private String date;
    private int hits;

    public BoardBody(int boardNo, String userId, String title, String content) {
        this.boardNo = boardNo;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hits = 0;
        this.date = DATE_FORMAT.format(new Date());
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("boardNo", boardNo);
        map.put("userId", userId);
        map.put("title", title);
        map.put("content", content);
        map.put("hits", hits);
        map.put("date", date);

        return map;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

}
