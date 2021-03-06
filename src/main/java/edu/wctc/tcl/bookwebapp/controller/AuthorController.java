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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author timothy
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String urlPathForAuthorPage = "/authorPage.jsp";
    private static final String authorPageAttributeName = "authors";
    private static final String authorPageAttributeNameForFindByID = "findAuthorById";
    private static final String DELETE_BUTTON = "delete";
    private static final String ID_CHECKBOX = "check1";
    private static final String CREATE_AUTHOR_BTN = "createAuthorbtn";
    private static final String CREATE_AUTHOR = "createAuthor";
    private static final String UPDATE_AUTHOR_BTN = "updateAuthorbtn";
    private static final String AUTHOR_NAME_FIELD = "authorNameField";
    private static final String ID_FIELD = "idField";
    private static final String GET_DETAILS = "getDetails";

    // db config init params from web.xml
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private String dbJndiName;

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

        if (request.getParameter(DELETE_BUTTON) != null) {
            String id = request.getParameter(ID_CHECKBOX);
            int result = authService.deleteAuthorById(id);
            System.out.println(result);

        } else if (request.getParameter(CREATE_AUTHOR_BTN) != null) {
            String authorName = request.getParameter(CREATE_AUTHOR);
            boolean r = authService.createNewAuthor(authorName);
            System.out.println(r);
        } else if (request.getParameter(UPDATE_AUTHOR_BTN) != null) {
            String authorName = request.getParameter(AUTHOR_NAME_FIELD);
            String id = request.getParameter(ID_FIELD);
            int result = authService.updateAuthorById(id, authorName);
            System.out.println(result);
        }

        if (request.getParameter(GET_DETAILS) != null && request.getParameter(ID_CHECKBOX) != null) {
            String id = request.getParameter(ID_CHECKBOX);
            List<Author> author = authService.getAuthorList();
            Author author2 = authService.getAuthorById(id);
            request.setAttribute(authorPageAttributeName, author);
            request.setAttribute(authorPageAttributeNameForFindByID, author2);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(urlPathForAuthorPage);
            dispatcher.forward(request, response);
        } else {
                HttpSession session = request.getSession();
            if (request.getParameter("welcomeName") != null) {
                String welcomeName = request.getParameter("welcomeName");
                String welcomeNameForSession = "Welcome " + welcomeName + ", On This Page You Can Add, Edit, Or Delete Authors.";
                session.setAttribute("welcomeNameForAuthorPage", welcomeNameForSession);
            } else if (request.getParameter("endSession") != null) {
                String endSessionWelcome = "Your Session Has Ended. Please Return to the Home Screen.";
                session.setAttribute("welcomeNameForAuthorPage", endSessionWelcome);
            }
            List<Author> author = authService.getAuthorList();
            request.setAttribute(authorPageAttributeName, author);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeRedirectURL(urlPathForAuthorPage));
            dispatcher.forward(request, response);
        }

    }

    private void configDbConnection() throws NamingException, Exception {
        if (dbJndiName == null) {
            authService.getDao().initDao(driverClass, url, userName, password);
        } else {
            /*
             Lookup the JNDI name of the Glassfish connection pool
             and then use it to create a DataSource object.
             */
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbJndiName);
            authService.getDao().initDao(ds);
        }
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
//        driverClass = getServletContext().getInitParameter("db.driver.class");
//        url = getServletContext().getInitParameter("db.url");
//        userName = getServletContext().getInitParameter("db.username");
//        password = getServletContext().getInitParameter("db.password");
        dbJndiName = getServletContext().getInitParameter("db.jndi.name");
    }

}
