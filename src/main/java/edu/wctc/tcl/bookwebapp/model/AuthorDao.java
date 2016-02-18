/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Author;

/**
 *
 * @author tliebl
 */
public class AuthorDao implements AuthorDaoStrategy {

    private DBStrategy db = new MySqlDBStrategy();
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://box1090.bluehost.com:3306/mygamepa_books";
    private final String USER = "mygamepa_ftrial";
    private final String PSW = "Nalani09";

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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        AuthorDaoStrategy dao = new AuthorDao();
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);
    }

}
