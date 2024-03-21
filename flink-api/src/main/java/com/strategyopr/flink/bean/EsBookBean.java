package com.strategyopr.flink.bean;

public class EsBookBean {
    String ID ;
    String Movie_Name_EN ;
    String Movie_Name_CN ;
    String Username;
    String Comment;

    public EsBookBean(String ID, String movie_Name_EN, String movie_Name_CN, String username, String comment) {
        this.ID = ID;
        Movie_Name_EN = movie_Name_EN;
        Movie_Name_CN = movie_Name_CN;
        Username = username;
        Comment = comment;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMovie_Name_EN() {
        return Movie_Name_EN;
    }

    public void setMovie_Name_EN(String movie_Name_EN) {
        Movie_Name_EN = movie_Name_EN;
    }

    public String getMovie_Name_CN() {
        return Movie_Name_CN;
    }

    public void setMovie_Name_CN(String movie_Name_CN) {
        Movie_Name_CN = movie_Name_CN;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
