/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.service;

import edu.wctc.tcl.bookwebapp.model.Author;
import edu.wctc.tcl.bookwebapp.model.Book;
import edu.wctc.tcl.bookwebapp.repository.AuthorRepository;
import edu.wctc.tcl.bookwebapp.repository.BookRepository;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author timothy
 */
@Repository
@Transactional(readOnly = true)
public class BookService {

    private transient final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Inject
    private BookRepository bookRepo;

    @Inject
    private AuthorRepository authorRepo;

    public BookService() {

    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(String id) {
        return bookRepo.findOne(new Integer(id));
    }

    /**
     * Spring performs a transaction with readonly=false. This guarantees a
     * rollback if something goes wrong.
     *
     * @param id
     */
    @Transactional
    public void deleteBookById(String id) {
        Book book = null;
        book = bookRepo.findOne(new Integer(id));
        bookRepo.delete(book);
    }

    /**
     * Spring performs a transaction with readonly=false. This guarantees a
     * rollback if something goes wrong.
     *
     * @param id
     * @param title
     * @param isbn
     * @param authorId
     */
    @Transactional
    public void updateAuthorById(String id, String title, String isbn, String authorId) {
        Book book = null;
        Author auth = null;
        book = bookRepo.findOne(new Integer(id));
        book.setBookId(new Integer(id));
        book.setTitle(title);
        book.setIsbn(isbn);
        auth = authorRepo.findOne(new Integer(authorId));
        book.setAuthorId(auth);
        bookRepo.saveAndFlush(book);
    }

    @Transactional
    public void createNewBook(String title, String isbn, String authorId) {
        Book book = new Book(0);
        Author auth = null;
        book.setTitle(title);
        book.setIsbn(isbn);
        auth = authorRepo.findOne(new Integer(authorId));
        book.setAuthorId(auth);
        bookRepo.saveAndFlush(book);
    }

}
