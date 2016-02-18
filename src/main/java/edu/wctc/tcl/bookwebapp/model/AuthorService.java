/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import model.Author;
/**
 *
 * @author tliebl
 */
public class AuthorService {

    private AuthorDaoStrategy dao = new AuthorDao();

    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorById(id);
    }
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        return dao.getAuthorList();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService as = new AuthorService();
        List<Author> authors = as.getAuthorList();
        System.out.println(authors);
    }
}
