package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Author;

import java.util.Optional;
import java.util.Set;

public interface AuthorService {

    Set<Author> findAll();
    Optional<Author> findById(long id);
    Optional<Author> findByName(String name);
    Author addAuthor(Author author);
    Author modifyAuthor(long id, Author newAuthor);
    void deleteAuthor(long id);
    Set<Author> findByIsAliveFalse();
    Set<Author> findNameContaining(String name);
    Set<Author> findQuery(boolean alive, boolean good, String editorial);
}
