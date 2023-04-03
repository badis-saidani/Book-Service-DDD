package com.book.domain.repository;

import com.book.domain.model.Book;
import java.util.List;
import java.util.NoSuchElementException;

public interface BookRepository {


    List<Book> getAllBooks();
    Book postReview(String id, String review) throws NoSuchElementException ;
    List<String> addBooks(List<Book> books);
    Book getBook(String id);

}

