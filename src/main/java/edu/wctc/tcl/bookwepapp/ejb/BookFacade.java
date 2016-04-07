/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwepapp.ejb;

import edu.wctc.tcl.bookwebapp.model.Author;
import edu.wctc.tcl.bookwebapp.model.Book;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author timothy
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "edu.wctc.tcl_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
     public void deleteAuthorById(String id) {
        Book book = this.find(new Integer(id));
        this.remove(book);
    }
    
    public void createNewBook(String title, String isbn, String authorId){
        Book book = new Book(0);
        Author author = null;
        book.setTitle(title);
        book.setIsbn(isbn);
        author = getEntityManager().find(Author.class, new Integer(authorId));
        book.setAuthorId(author);
        this.getEntityManager().merge(book);
    }
    
    public void updateAuthorById(String id, String title, String isbn, String authorId){
        Book book = this.find(new Integer(id));
        Author author = null;
        book.setBookId(new Integer(id));
        book.setTitle(title);
        book.setIsbn(isbn);
        author = getEntityManager().find(Author.class, new Integer(authorId));
        book.setAuthorId(author);
        this.getEntityManager().merge(author);
    }
}
