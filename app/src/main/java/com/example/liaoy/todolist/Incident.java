package com.example.liaoy.todolist;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by liaoy on 2017/11/12.
 */

public class Incident extends DataSupport implements Serializable{

    private String title;
    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
