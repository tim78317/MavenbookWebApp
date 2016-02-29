/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.controller;

import edu.wctc.tcl.bookwebapp.model.Author;
import edu.wctc.tcl.bookwebapp.model.AuthorService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author timothy
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String urlPathForAuthorPage = "/authorPage.jsp";
    private static final String authorPageAttributeName = "authors";
    private static final String authorPageAttributeNameForFindByID = "findAuthorById";

    // db config init params from web.xml
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    @Inject
    private AuthorService authService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        configDbConnection();

        if (request.getParameter("delete") != null) {
            String id = request.getParameter("check1");
            int result = authService.deleteAuthorById(id);
            System.out.println(result);

        } else if (request.getParameter("createAuthorbtn") != null) {
            String authorName = request.getParameter("createAuthor");
            boolean r = authService.createNewAuthor(authorName);
            System.out.println(r);
        } else if (request.getParameter("updateAuthorbtn") != null) {
            String authorName = request.getParameter("authorNameField");
            String id = request.getParameter("idField");
            int result = authService.updateAuthorById(id, authorName);
            System.out.println(result);
        }

        if (request.getParameter("getDetails") != null && request.getParameter("check1") != null) {
            String id = request.getParameter("check1");
            List<Author> author = authService.getAuthorList();
            Author author2 = authService.getAuthorById(id);
            request.setAttribute(authorPageAttributeName, author);
            request.setAttribute(authorPageAttributeNameForFindByID, author2);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(urlPathForAuthorPage);
            dispatcher.forward(request, response);
        } else {
            List<Author> author = authService.getAuthorList();
            request.setAttribute(authorPageAttributeName, author);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(urlPathForAuthorPage);
            dispatcher.forward(request, response);
        }

    }

    private void configDbConnection() {
        authService.getDao().initDao(driverClass, url, userName, password);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Called after the constructor is called by the container. This is the
     * correct place to do one-time initialization.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // Get init params from web.xml
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
    }

}
