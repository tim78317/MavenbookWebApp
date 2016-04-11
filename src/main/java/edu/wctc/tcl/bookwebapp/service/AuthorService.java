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
import java.util.Date;
import java.util.LinkedHashSet;
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
public class AuthorService {
    private transient final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    @Inject
    private AuthorRepository authorRepo;

    @Inject
    private BookRepository bookRepo;

    public AuthorService() {
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }
    
    /**
     * This custom method is necessary because we are using Hibernate which
     * does not load lazy relationships (in this case Books).
     * @param id
     * @return 
     */
    public Author findByIdAndFetchBooksEagerly(String id) {
        Integer authorId = new Integer(id);
        
        // You could do this to eagerly load the books, but it's slow
//        return authorRepo.findByIdAndFetchBooksEagerly(authorId);

        // Instead do this, it's faster
        Author author = authorRepo.findOne(authorId);
        author.getBookCollection().size();
        return author;
    }

    public Author findById(String id) {
        return authorRepo.findOne(new Integer(id));
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param id 
     */
    @Transactional
    public void deleteAuthorById(String id) {
        Author author = null; 
        author =  authorRepo.findOne(new Integer(id));
        authorRepo.delete(author);
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param id 
     * @param name
     */
    @Transactional
    public void updateAuthorById(String id, String name) {
        Author auth = new Author();
        auth.setAuthorId(new Integer(id));
        auth.setAuthorName(name);
    }
    
     @Transactional
    public void createNewAuthor(String name) {
       Author auth = new Author(0);
       auth.setAuthorName(name);
       auth.setDateAdded(new Date());
    }
}
