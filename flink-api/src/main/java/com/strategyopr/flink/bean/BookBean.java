package com.strategyopr.flink.bean;

public class BookBean {
     String ID ;
     String Movie_Name_EN ;
    String Movie_Name_CN ;
    String Crawl_Date ;
    String Number;
    String Username;
    String Date;
    String Star;
    String Comment;
    String Like;

    public BookBean(String ID, String movie_Name_EN, String movie_Name_CN, String crawl_Date, String number, String username, String date, String star, String comment, String like) {
        this.ID = ID;
        Movie_Name_EN = movie_Name_EN;
        Movie_Name_CN = movie_Name_CN;
        Crawl_Date = crawl_Date;
        Number = number;
        Username = username;
        Date = date;
        Star = star;
        Comment = comment;
        Like = like;
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

    public String getCrawl_Date() {
        return Crawl_Date;
    }

    public void setCrawl_Date(String crawl_Date) {
        Crawl_Date = crawl_Date;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getLike() {
        return Like;
    }

    public void setLike(String like) {
        Like = like;
    }
}
