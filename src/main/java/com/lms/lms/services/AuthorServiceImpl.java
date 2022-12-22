package com.lms.lms.services;

import com.lms.lms.data.models.Author;
import com.lms.lms.data.repositories.AuthorRepository;
import com.lms.lms.dtos.request.CreateAuthorRequest;
import com.lms.lms.dtos.response.CreateAuthorResponse;
import com.lms.lms.exceptions.AuthorException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CreateAuthorResponse createAuthor(CreateAuthorRequest createAuthorRequest) {
        if (authorRepository.findAuthorByEmail(createAuthorRequest.getEmail().toLowerCase()).isPresent())throw new AuthorException("Author already exists");
        Author author = Author.builder().
                email(createAuthorRequest.getEmail().toLowerCase()).
                firstName(createAuthorRequest.getFirstName()).
                lastName(createAuthorRequest.getLastName()).build();
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, CreateAuthorResponse.class);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(()-> new AuthorException("Author with id "+ id +" does not exist"));
    }

    @Override
    public Author getAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email).orElseThrow(()-> new AuthorException("Author does not exist"));
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public String deleteAuthorByEmail(String email) {
        getAuthorByEmail(email);
        authorRepository.deleteAuthorByEmail(email);
        return "Admin deleted successfully";
    }


}
