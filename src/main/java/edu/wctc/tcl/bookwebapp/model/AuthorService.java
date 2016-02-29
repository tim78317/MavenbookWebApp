/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author tliebl
 */
@SessionScoped
public class AuthorService implements Serializable {

    @Inject
    private AuthorDaoStrategy dao;

//    private AuthorDaoStrategy dao = new AuthorDao();
    /**
     * default constructor required for injectable objects
     */
    public AuthorService() {

    }

    public Author getAuthorById(String authorId) throws Exception, SQLException {
        return dao.getAuthorById(Integer.parseInt(authorId));
    }

    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        return dao.deleteAuthorById(id);
    }

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        return dao.getAuthorList();
    }

    public int updateAuthorById(Object id, String authorName) throws ClassNotFoundException, SQLException {
        return dao.updateAuthorById(id, authorName);
    }

    public boolean createNewAuthor(String authorName) throws ClassNotFoundException, SQLException {
        return dao.insertAuthor(authorName);
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        AuthorService as = new AuthorService();
////        List<Author> authors = as.getAuthorList();
//        int result = as.updateAuthorById(3, "Sally Sara");
//        System.out.println(result);
//    }
}
