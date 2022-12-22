package com.lms.lms.services;

import com.lms.lms.data.models.Author;
import com.lms.lms.dtos.request.CreateBookRequest;
import com.lms.lms.dtos.response.CreateBookResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class BookServiceImplTest {
private CreateBookRequest createBookRequest;
@Autowired
private BookService bookService;

private CreateBookResponse createBookResponse;
    @BeforeEach
    void setUp() {

        createBookRequest = new CreateBookRequest();
        createBookRequest.setBookName("First Book");
        createBookRequest.setIsbn(123456L);
        createBookRequest.setQuantity(3);
        Author author = new Author();
        author.setFirstName("Kabir");
        author.setLastName("Yusuf");
        author.setEmail("kabir@gmail.com");
//        createBookRequest.setAuthor(author);
//        createBookResponse = bookService.saveBook(createBookRequest);

    }

    @AfterEach
    void tearDown() {
        bookService.deleteAll();
    }
    @Test
    void testThatBookCanBeCreated(){
        assertNotNull(createBookResponse.getId());
    }
}