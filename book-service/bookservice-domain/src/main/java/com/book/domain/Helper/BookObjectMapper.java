package com.book.domain.Helper;

import com.book.domain.model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class BookObjectMapper {
    public static Book parseBookObject(JSONObject book)
    {
        //Get book object within list
        JSONObject bookObject = book;

        //Get book details
        String uid = (String) bookObject.get("uid");
        String title = (String) bookObject.get("title");
        String author = (String) bookObject.get("author");
        String genre = (String) bookObject.get("genre");
        double price = (Double) bookObject.get("price");
        JSONArray reviewList = (JSONArray) bookObject.get("reviews");
        List<String> reviews = (List<String>) reviewList.stream().map(r-> r.toString()).collect(Collectors.toList());

        Book bookDetails = new Book(title,author,genre,price);
        bookDetails.setReviews(reviews);

        return bookDetails;

    }

    public static JSONObject parseObjectBook(Book book) {
        JSONObject object = new JSONObject();
        object.put("uid",book.getUid());
        object.put("title",book.getTitle());
        object.put("author",book.getAuthor());
        object.put("genre",book.getGenre());
        object.put("price",book.getPrice());
        object.put("reviews",book.getReviews());
        return object;
    }
}
