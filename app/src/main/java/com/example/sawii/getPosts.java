package com.example.sawii;

import java.util.Date;

public class getPosts {
    private int  id;
    private int  pat_id;
    private String post_date_time;
    private String post_content;

    public int getId() {
        return id;
    }

    public int getPat_id() {
        return pat_id;
    }

    public String getPost_date_time() {
        return post_date_time;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPat_id(int pat_id) {
        this.pat_id = pat_id;
    }

    public void setPost_date_time(String post_date_time) {
        this.post_date_time = post_date_time;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }
}
