package com.aaron.AA2_AD.repository;

import com.aaron.AA2_AD.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Set<Author> findAll();
    Optional<Author> findByName(String name);
    Set<Author> findByIsAliveFalse();
    Set<Author> findByNameContaining(String name);
    @Query(value = "select * from author a inner join book b on b.author_id=a.id where " +
            "a.is_alive = :alive and b.is_good = :good and b.editorial = :editorial", nativeQuery = true)
    Set<Author> findQuery(boolean alive, boolean good, String editorial);
}
