package com.aaron.AA2_AD.repository;

import com.aaron.AA2_AD.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Long> {

    Set<Book> findAll();
    Book findByTitle(String title);
}
