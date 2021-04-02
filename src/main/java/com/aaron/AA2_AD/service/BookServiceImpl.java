package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Book;
import com.aaron.AA2_AD.exception.BookNotFoundException;
import com.aaron.AA2_AD.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Set<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book modifyBook(long id, Book newBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
        newBook.setId(book.getId());
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
        bookRepository.deleteById(id);
    }
}
