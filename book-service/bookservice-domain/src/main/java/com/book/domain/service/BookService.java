package com.book.domain.service;

import com.book.domain.model.Book;
import com.book.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public  List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }
    public Book postReview(String id, String review){
        return bookRepository.postReview(id,review);
    }

    public List<String> addBooks(List<Book> books){
        return bookRepository.addBooks(books);
    }

    public Book getBook(String id){
        return bookRepository.getBook(id);
    }
}
