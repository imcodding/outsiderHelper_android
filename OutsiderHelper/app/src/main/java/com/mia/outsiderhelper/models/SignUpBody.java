package com.mia.outsiderhelper.models;

import java.util.HashMap;

public class SignUpBody {
   private String userId;
   private String password;
   private String name;
   private String nickname;
   private String age;
   private String university;

    public SignUpBody(String userId, String password, String name, String nickname, String age) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("hash", password);
        map.put("nickname", nickname);
        map.put("age", age);

        return map;
    }
}
