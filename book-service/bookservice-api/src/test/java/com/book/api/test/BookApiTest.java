package com.book.api.test;

import com.book.domain.model.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class BookApiTest {



    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost/";
        RestAssured.basePath = "";
    }
    @Test
    public void getAllBooks() {
        given()
                .when()
                .get("books")
                .then()
                .statusCode(200);
    }

    @Test
    public void postBook(){
        List<Book> books = new ArrayList<>();
        Book b = new Book("school","med","action",20);
//        b.addReview("behi");
        Book b2 = new Book("univ","ach","horror",5.08);
//        List<Book>  books = new ArrayList<>();
        books.add(b);
        books.add(b2);

        given()
                .contentType("application/json")
                .body(books)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .contentType("application/json")
                .when().get("/books/medschool").then()
                .statusCode(200)
                .and()
                .body("price", equalTo(20f));

        given()
                .contentType("application/json")
                .when().get("/books/achuniv").then()
                .statusCode(200)
                .and()
                .body("price",equalTo(5.08f));

    }

    @Test
    public void addReview(){
        String review = "Amazing Book!";

        given()
                .contentType(ContentType.TEXT)
                .body(review)
                .when().post("/books/medschool/reviews").then()
                .statusCode(200)
                .and()
                .body("reviews",hasItem("Amazing Book!"));

    }
}
