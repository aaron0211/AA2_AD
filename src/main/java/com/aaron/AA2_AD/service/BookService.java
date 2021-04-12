package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Book;
import com.aaron.AA2_AD.domain.dto.BookDTO;

import java.util.Optional;
import java.util.Set;

public interface BookService {

    Set<Book> findAll();
    Optional<Book> findById(long id);
    Optional<Book> findByTitle(String title);
    Book addBook(BookDTO bookDTO);
    Book modifyBook(long id, Book newBook);
    void deleteBook(long id);
}
