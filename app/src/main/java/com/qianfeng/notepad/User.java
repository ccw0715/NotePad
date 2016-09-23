package com.qianfeng.notepad;

import java.io.Serializable;

/**
 * Created by 蔡灿武 on 2016/9/16.
 */
public class User implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "用户：[" + "用户名：" + username + ",密码：" + password + "]";
    }
}
