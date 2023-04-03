package com.book.domain.repository;

import com.book.domain.FileUtil;
import com.book.domain.Helper.BookObjectMapper;
import com.book.domain.model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private  List<Book> bookList;

    @Value("${app.file}")
    private  String filename;
    @Override
    public  List<Book> getAllBooks(){
        refreshBookList();
        return bookList;
    }

    private void refreshBookList() {
        bookList = new ArrayList();
        JSONArray books = FileUtil.readFromFile(filename);
        //Iterate over book array
        books.forEach( b -> bookList.add(BookObjectMapper.parseBookObject( (JSONObject) b) ) );
    }
    @Override
    public  Book postReview(String id, String review) throws NoSuchElementException {
        try {
            refreshBookList();
        }catch (Exception e){
            System.err.println("The file doesn't exist!");
        }

        Book book = new Book();
        boolean notFound = true;
        for (Book b: bookList) {
            if(b.getUid().equalsIgnoreCase(id)) {
                b.addReview(review);
                notFound = false;
                book = b;
                break;
            }
        }
        if(notFound) throw new NoSuchElementException("Book with id= "+id+" is not found!");
        updateFile();
        return book;

    }
    @Override
    public  List<String> addBooks(List<Book> books){
        try {
            refreshBookList();
        }catch (Exception e){
            System.err.println("The file doesn't exist! We're writing a new one!");
        }
        books.forEach(b->b.setUid((b.getAuthor()+b.getTitle()).replaceAll("\\s", "")));
        bookList.addAll(books);
        updateFile();
        return (List<String>) bookList.stream().map(b-> b.getUid()).collect(Collectors.toList());
    }

    private void updateFile() {
        //add Books to the list
        JSONArray books = new JSONArray();
        bookList.forEach(book -> books.add(BookObjectMapper.parseObjectBook(book)));
        FileUtil.writeInFile(books, filename);
    }
    @Override
    public  Book getBook(String id){
        if(bookList.isEmpty())
            refreshBookList();
        Optional<Book> result = bookList
                .stream().parallel()
                .filter(b -> b.getUid().equalsIgnoreCase(id)).findAny();
        if(result.isPresent())
            return result.get();
        else return null;
    }

}
