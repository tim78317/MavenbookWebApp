package edu.wctc.tcl.bookwebapp.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Author;

/**
 *
 * @author timothy
 */
public class MockAuthorDao implements AuthorDaoStrategy{

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
}
