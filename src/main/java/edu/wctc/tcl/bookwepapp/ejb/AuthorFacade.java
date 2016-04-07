/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwepapp.ejb;

import edu.wctc.tcl.bookwebapp.model.Author;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author timothy
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.tcl_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public void deleteAuthorById(String id) {
        Author author = this.find(new Integer(id));
        this.remove(author);
    }
    
    public void createNewAuthor(String name){
        Author author = new Author(0);
        author.setAuthorName(name);
        author.setDateAdded(new Date());
        this.getEntityManager().merge(author);
    }
    
    public void updateAuthorById(String id, String name){
        Author author = this.find(new Integer(id));
        author.setAuthorId(new Integer(id));
        author.setAuthorName(name);
        this.getEntityManager().merge(author);
    }
}
