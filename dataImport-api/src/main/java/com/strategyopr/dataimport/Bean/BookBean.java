package com.strategyopr.dataimport.Bean;

import java.util.ArrayList;

/**
 *    String bookId = getBookId(url);
 *             ArrayList<String> comments = getComments(url);
 *             String info = getInfo(url);
 *             String score = getScore(url);
 *             String bookName = getBookName(url);
 *             String description = getDescription(url);
 */
public class BookBean {
    int bookId;
    String bookName;
    String info;
    Double score;
    String description;
    ArrayList<String> comments;

    public BookBean(int bookId, String bookName, String info, Double score, String description, ArrayList<String> comments) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.info = info;
        this.score = score;
        this.description = description;
        this.comments = comments;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return  bookId+"," +bookName + "," +info +"," +score +","+ description+"," +comments.toString();
    }
}
