package com.lms.lms.services;

import com.lms.lms.data.models.Book;
import com.lms.lms.dtos.request.CreateBookRequest;
import com.lms.lms.dtos.response.CreateBookResponse;


import java.util.List;

public interface BookService {
//    CreateBookResponse saveBook(CreateBookRequest createBookRequest);
    String deleteBookById();
    String deleteAllBooks();
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book getBookByName(String bookName);
    List<Book> getBooksByAuthor(String authorName);


    void deleteAll();
}
