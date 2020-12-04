package com.mia.outsiderhelper.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import static com.mia.outsiderhelper.ApplicationClass.DATE_FORMAT;

public class CommentBody implements Serializable {
    private int commentNo;
    private String content;
    private String userId;
    private String date;

    public CommentBody(){}
    public CommentBody(int commentNo, String content, String userId) {
        this.commentNo = commentNo;
        this.content = content;
        this.userId = userId;
        this.date = DATE_FORMAT.format(new Date());
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("commentNo", commentNo);
        map.put("content", content);
        map.put("userId", userId);
        map.put("date", date);

        return map;
    }

    public CommentBody toObject(HashMap<String, Object> map) {
        this.commentNo = Integer.parseInt(String.valueOf(map.get("commentNo")));
        this.content = String.valueOf(map.get("content"));
        this.userId = String.valueOf(map.get("userId"));
        this.date = String.valueOf(map.get("date"));

        return this;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
