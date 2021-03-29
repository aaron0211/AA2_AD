package com.aaron.AA2_AD.repository;

import com.aaron.AA2_AD.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Set<Author> findAll();
    Author findByName(String name);
}
