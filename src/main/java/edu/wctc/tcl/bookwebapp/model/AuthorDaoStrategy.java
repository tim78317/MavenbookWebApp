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
public interface AuthorDaoStrategy {

    public abstract List<Author> getAuthorList() throws ClassNotFoundException, SQLException;

    public abstract int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException;

    public int updateAuthorById(Object authorId, String authorName) throws ClassNotFoundException, SQLException;

    public boolean insertAuthor(String authorName) throws SQLException, ClassNotFoundException;

    public DBStrategy getDb();

    public void setDb(DBStrategy db);

}
