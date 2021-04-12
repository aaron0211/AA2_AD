package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Author;
import com.aaron.AA2_AD.domain.Book;
import com.aaron.AA2_AD.domain.dto.BookDTO;
import com.aaron.AA2_AD.exception.AuthorNotFoundException;
import com.aaron.AA2_AD.exception.BookNotFoundException;
import com.aaron.AA2_AD.repository.AuthorRepository;
import com.aaron.AA2_AD.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Set<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Author author = authorRepository.findByName(bookDTO.getNameAuthor())
                .orElseThrow(()-> new AuthorNotFoundException("Author not found"));
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDatePurchase(bookDTO.getDatePurchase());
        book.setIsbn(bookDTO.getIsbn());
        book.setEditorial(bookDTO.getEditorial());
        book.setPrize(bookDTO.getPrize());
        book.setIsGood(true);
        book.setAuthor(author);
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
