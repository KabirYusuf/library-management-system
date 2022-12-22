package com.lms.lms.data.repositories;

import com.lms.lms.data.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByIsbn(Long isbn);
}
