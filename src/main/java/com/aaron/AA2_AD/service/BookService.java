package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Book;

import java.util.Optional;
import java.util.Set;

public interface BookService {

    Set<Book> findAll();
    Optional<Book> findById(long id);
    Book addBook(Book book);
    Book modifyBook(long id, Book newBook);
    void deleteBook(long id);
}
