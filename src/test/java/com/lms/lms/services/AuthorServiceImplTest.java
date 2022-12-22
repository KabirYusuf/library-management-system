package com.lms.lms.services;

import com.lms.lms.data.models.Author;
import com.lms.lms.dtos.request.CreateAuthorRequest;
import com.lms.lms.dtos.response.CreateAuthorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;
    private CreateAuthorResponse createAuthorResponse;
    private CreateAuthorRequest createAuthorRequest;

    @BeforeEach
    void setUp() {
//        authorService.deleteAll();
        createAuthorRequest = new CreateAuthorRequest();
        createAuthorRequest.setEmail("author@gmail.com");
        createAuthorRequest.setFirstName("AuthorFirstName");
        createAuthorRequest.setLastName("AuthorLastName");

    }

    @AfterEach
    void tearDown() {
        authorService.deleteAll();
    }
    @Test
    void testThatAuthorCanBeCreated(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        assertNotNull(createAuthorResponse.getId());
        System.out.println(createAuthorResponse.getId());
       assertEquals("Successful", createAuthorResponse.getMessage());
    }
    @Test
    void findAuthorByEmail(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        Author foundAuthor = authorService.getAuthorByEmail("author@gmail.com");
        assertEquals("AuthorFirstName", foundAuthor.getFirstName());
    }
    @Test
    void deleteAuthorByEmail(){
        createAuthorResponse = authorService.createAuthor(createAuthorRequest);
        List<Author> authorsBeforeDeletion = authorService.getAuthors();
        assertEquals(1, authorsBeforeDeletion.size());
        authorService.deleteAuthorByEmail("author@gmail.com");
        List<Author> authorsAfterDeletion = authorService.getAuthors();
        assertEquals(0, authorsAfterDeletion.size());
    }
}