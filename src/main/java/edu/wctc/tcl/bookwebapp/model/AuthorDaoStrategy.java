/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author tliebl
 */
public interface AuthorDaoStrategy {

    public abstract List<Author> getAuthorList() throws ClassNotFoundException, SQLException, Exception;

    public abstract int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException, Exception;

    public int updateAuthorById(Object authorId, String authorName) throws ClassNotFoundException, SQLException, Exception;

    public boolean insertAuthor(String authorName) throws SQLException, ClassNotFoundException, Exception;

    public Author getAuthorById(Integer authorId) throws SQLException, Exception;

    public DBStrategy getDb();

    public void setDb(DBStrategy db);

    public void initDao(String driver, String url, String user, String password);

    public void initDao(DataSource ds) throws Exception;

    public String getDriver();

    public void setDriver(String driver);

    public String getUrl();

    public void setUrl(String url);

    public String getUser();

    public void setUser(String user);

    public String getPwd();

    public void setPwd(String pwd);

}
