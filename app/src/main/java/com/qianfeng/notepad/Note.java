package com.qianfeng.notepad;

import java.io.Serializable;

/**
 * Created by 蔡灿武 on 2016/9/17.
 */
public class Note implements Serializable{
    private String username;
    private String time;
    private String title;
    private String content;

    public Note(String username, String time, String title, String content) {
        this.username = username;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Note{" +
                "username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
