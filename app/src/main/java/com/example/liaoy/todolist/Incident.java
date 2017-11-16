package com.example.liaoy.todolist;

import java.io.Serializable;

/**
 * Created by liaoy on 2017/11/12.
 */

public class Incident implements Serializable{
    private String title;
    private String content;

    public Incident(String title,String content){
        this.title=title;
        this.content=content;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
