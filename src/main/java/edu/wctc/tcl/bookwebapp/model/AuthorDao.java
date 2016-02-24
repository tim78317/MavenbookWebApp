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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import model.Author;

/**
 *
 * @author tliebl
 */
@SessionScoped
public class AuthorDao implements AuthorDaoStrategy, Serializable {
    @Inject
    private DBStrategy db;
   
//    private DBStrategy db = new MySqlDBStrategy();

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://box1090.bluehost.com:3306/mygamepa_books";
    private final String USER = "mygamepa_ftrial";
    private final String PSW = "Nalani09";

    public AuthorDao(){
        
    }
   @Override
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(DRIVER, URL, USER, PSW);
        int result = db.deleteById("mygamepa_books", "author", "author_id", id);
        db.closeConnection();
        return result;
    }

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {

        db.openConnection(DRIVER, URL, USER, PSW);
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
        db.openConnection(DRIVER, URL, USER, PSW);
        int result = db.updateRecordById("author", Arrays.asList("author_name"), Arrays.asList(authorName), "author_id", authorId);
        db.closeConnection();
        return result;
    }

    @Override
    public boolean insertAuthor(String authorName) throws SQLException, ClassNotFoundException {
       db.openConnection(DRIVER, URL, USER, PSW);
       boolean r = db.insertRecord("author", Arrays.asList("author_name", "date_added"),  Arrays.asList(authorName, new Date()), true);
       return r;
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

}
