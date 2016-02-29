package edu.wctc.tcl.bookwebapp.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author timothy
 */
@Alternative
@Dependent
public class MockAuthorDao implements AuthorDaoStrategy, Serializable{

    private DBStrategy db;
    private static final DateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
    private static final String strDate1 = "11/06/2007";
    private static final String strDate2 = "09/03/2009";
    private static final String strDate3 = "01/01/2001";
    private static final String authName1 = "Tim Po";
    private static final String authName2 = "John Smith";
    private static final String authName3 = "Sally Sue";
    private static final String errMsg = "Not Parsing";
    private static final int setAuthIdForlist1 = 0;
    private static final int setAuthIdForlist2 = 1;
    private static final int setAuthIdForlist3 = 2;
    private static final Author auth1 = new Author();
    private static final Author auth2 = new Author();
    private static final Author auth3 = new Author();
    private static final List<Author> aL = new ArrayList<>();
    
    public MockAuthorDao(){
        
    }
    
    public List<Author> getAuthorList() {
        

        try {
            auth1.setAuthorId(setAuthIdForlist1);
            auth1.setAuthorName(authName1);
            Date date1 = (Date) dF.parse(strDate1);
            auth1.setDateAdded(date1);
            aL.add(auth1);
            auth2.setAuthorId(setAuthIdForlist2);
            auth2.setAuthorName(authName2);
            Date date2 = (Date) dF.parse(strDate2);
            auth2.setDateAdded(date2);
            aL.add(auth2);
            auth3.setAuthorId(setAuthIdForlist3);
            auth3.setAuthorName(authName3);
            Date date3 = (Date) dF.parse(strDate3);
            auth3.setDateAdded(date3);
            aL.add(auth3);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            System.out.printf(errMsg);

        }
        return aL;
    }

    @Override
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAuthorById(Object authorId, String authorName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertAuthor(String authorName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBStrategy getDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDb(DBStrategy db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initDao(String driver, String url, String user, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriver(String driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPwd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPwd(String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author getAuthorById(Integer authorId) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
