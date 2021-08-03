package com.example.sawii;

public class postSignUp {
    private int user_id;
    private int  major;
    private String  username;
    private String  user_email;
    private String  user_pass;
    private int  user_gender;
    private String  synd_pic;

    public postSignUp(int major, String username, String user_email, String user_pass, int user_gender, String synd_pic) {
        this.major = major;
        this.username = username;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_gender = user_gender;
        this.synd_pic = synd_pic;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public String getSynd_pic() {
        return synd_pic;
    }

    public void setSynd_pic(String synd_pic) {
        this.synd_pic = synd_pic;
    }
}
