/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author tliebl
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy, Serializable {

    @Inject
    private DBStrategy db;

//    private DBStrategy db = new MySqlDBStrategy();
    private String driver;
    private String url;
    private String user;
    private String pwd;

    public AuthorDao() {

    }

    @Override
    public void initDao(String driver, String url, String user, String password) {
        setDriver(driver);
        setUrl(url);
        setUser(user);
        setPwd(password);
    }

    @Override
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, user, pwd);
        int result = db.deleteById("mygamepa_books", "author", "author_id", id);
        db.closeConnection();
        return result;
    }

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        db.openConnection(driver, url, user, pwd);
        List<Map<String, Object>> rawData = db.findAllRecords("author", 10);
        List<Author> authors = new ArrayList<>();

        for (Map rec : rawData) {
            Author author = new Author();
            Integer id = (Integer) rec.get("author_id");
            author.setAuthorId(id);
            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rec.get("date_added") == null ? null : (Date) rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }

        db.closeConnection();
        return authors;
    }

    @Override
    public int updateAuthorById(Object authorId, String authorName) throws SQLException, ClassNotFoundException {
        db.openConnection(driver, url, user, pwd);
        int result = db.updateRecordById("author", Arrays.asList("author_name"), Arrays.asList(authorName), "author_id", authorId);
        db.closeConnection();
        return result;
    }

    @Override
    public boolean insertAuthor(String authorName) throws SQLException, ClassNotFoundException {
        db.openConnection(driver, url, user, pwd);
        boolean r = db.insertRecord("author", Arrays.asList("author_name", "date_added"), Arrays.asList(authorName, new Date()), true);
        return r;
    }

    @Override
     public Author getAuthorById(Integer authorId)throws SQLException, Exception {
        db.openConnection(driver, url, user, pwd);
        
        Map<String,Object> rawRec = db.findById("author", "author_id", authorId);
        Author author = new Author();
        author.setAuthorId((Integer)rawRec.get("author_id"));
        author.setAuthorName(rawRec.get("author_name").toString());
        author.setDateAdded((Date)rawRec.get("date_added"));
        
        return author;
    }
     
    @Override
    public DBStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DBStrategy db) {
        this.db = db;
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//        AuthorDaoStrategy dao = new AuthorDao();
//        //List<Author> authors = dao.getAuthorList();
//        //System.out.println(authors);
//        boolean result = dao.insertAuthor("Tim Duncan");
//        System.out.println(result);
//        
//    }
    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String psw) {
        this.pwd = psw;
    }

}
