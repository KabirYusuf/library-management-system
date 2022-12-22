package com.lms.lms.services;

import com.lms.lms.data.models.Book;
import com.lms.lms.data.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
//    @Override
//    public CreateBookResponse saveBook(CreateBookRequest createBookRequest) {
//        if (bookRepository.findBookByIsbn(createBookRequest.getIsbn()).isPresent())throw new BookException("Book already exist");
//        Author foundAuthor = authorService.findAuthorById(createBookRequest.getAuthorId());
//    }

    @Override
    public String deleteBookById() {
        return null;
    }

    @Override
    public String deleteAllBooks() {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book getBookByName(String bookName) {
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        return null;
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
