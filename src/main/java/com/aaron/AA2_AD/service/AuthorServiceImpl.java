package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Author;
import com.aaron.AA2_AD.exception.AuthorNotFoundException;
import com.aaron.AA2_AD.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl  implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Set<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name){
        return authorRepository.findByName(name);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author modifyAuthor(long id, Author newAuthor) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException(id));
        newAuthor.setId(author.getId());
        return authorRepository.save(newAuthor);
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException(id));
        authorRepository.deleteById(id);
    }

    @Override
    public Set<Author> findByIsAliveFalse() {
        return authorRepository.findByIsAliveFalse();
    }

    @Override
    public Set<Author> findNameContaining(String name) {
        return authorRepository.findByNameContaining(name);
    }

    @Override
    public Set<Author> findQuery(boolean alive, boolean good, String editorial) {
        return authorRepository.findQuery(alive, good, editorial);
    }
}
