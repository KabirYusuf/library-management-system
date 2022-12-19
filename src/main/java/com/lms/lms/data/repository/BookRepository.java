package com.lms.lms.data.repository;

import com.lms.lms.data.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
