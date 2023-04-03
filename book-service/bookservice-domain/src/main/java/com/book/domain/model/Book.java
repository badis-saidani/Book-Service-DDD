package com.book.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String uid;
    private String title;
    private String author;
    private String genre;
    private double price;
    private List<String> reviews;

    public Book(){
        reviews = new ArrayList<>();
    }

    public Book(String title, String author, String genre, double price) {
        this();
        this.uid = author+title;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public void addReview(String review){
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Book{" +
                "uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", reviews=" + reviews +
                '}';
    }
}
