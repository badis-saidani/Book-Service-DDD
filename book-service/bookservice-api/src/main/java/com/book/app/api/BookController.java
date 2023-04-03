package com.book.app.api;


import com.book.domain.model.Book;
import com.book.domain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/books",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> postBooks(@RequestBody List<Book> books) {
            List<String> res = bookService.addBooks(books);
            return new ResponseEntity<List<String>>(res, HttpStatus.OK);
    }
    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
//        System.out.println(filename);
        List<Book> res = bookService.getAllBooks();
        return new ResponseEntity<List<Book>>(res, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") String id) {
        Book res = bookService.getBook(id);
        if(res==null)
            return new ResponseEntity<String>("Book with id:"+id+" is not found!", HttpStatus.OK);
        else
            return new ResponseEntity<Book>(res, HttpStatus.OK);
    }

    @PostMapping("/books/{id}/reviews")
    public ResponseEntity<?> postReview(@PathVariable("id") String id, @RequestBody String review) {
        Book res = new Book();
        try {
            res = bookService.postReview(id,review);
        }catch (NoSuchElementException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }
            return new ResponseEntity<Book>(res, HttpStatus.OK);
    }
}
