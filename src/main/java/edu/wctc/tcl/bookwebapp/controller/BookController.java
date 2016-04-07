/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tcl.bookwebapp.controller;

import edu.wctc.tcl.bookwebapp.model.Author;
import edu.wctc.tcl.bookwebapp.model.Book;
import edu.wctc.tcl.bookwepapp.ejb.BookFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author timothy
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    private static final String urlPathForBookPage = "/booksPage.jsp";
    private static final String bookPageAttributeName = "books";
    private static final String bookPageAttributeNameForFindByID = "findBookById";
    private static final String DELETE_BUTTON = "delete";
    private static final String ID_CHECKBOX = "check1";
    private static final String CREATE_AUTHOR_BTN = "createBookbtn";
    private static final String CREATE_BOOK_TITLE = "createBookTitle";
    private static final String CREATE_BOOK_ISBN = "createBookISBN";
    private static final String CREATE_AUTHOR_ID = "createAuthorID";
    private static final String UPDATE_AUTHOR_BTN = "updateBookbtn";
    private static final String BOOK_TITLE_FIELD = "bookTitleField";
    private static final String BOOK_ISBN_FIELD = "bookISBNField";
    private static final String BOOK_AUTHORID_FIELD = "authorIdField";
    private static final String ID_FIELD = "idField";
    private static final String GET_DETAILS = "getDetails";

    @Inject
    private BookFacade bookService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter(DELETE_BUTTON) != null) {
            String id = request.getParameter(ID_CHECKBOX);
            bookService.deleteAuthorById(id);
        } else if (request.getParameter(CREATE_AUTHOR_BTN) != null) {
            String title = request.getParameter(CREATE_BOOK_TITLE);
            String isbn = request.getParameter(CREATE_BOOK_ISBN);
            String authorId = request.getParameter(CREATE_AUTHOR_ID);
            bookService.createNewBook(title, isbn, authorId);
        } else if (request.getParameter(UPDATE_AUTHOR_BTN) != null) {
            String title = request.getParameter(BOOK_TITLE_FIELD);
            String isbn = request.getParameter(BOOK_ISBN_FIELD);
            String authorId = request.getParameter(BOOK_AUTHORID_FIELD);
            String id = request.getParameter(ID_FIELD);
            bookService.updateAuthorById(id,title,isbn,authorId);
        }

        if (request.getParameter(GET_DETAILS) != null && request.getParameter(ID_CHECKBOX) != null) {
            String id = request.getParameter(ID_CHECKBOX);
            List<Book> book = bookService.findAll();
            Book book2 = bookService.find(new Integer(id));
            request.setAttribute(bookPageAttributeName, book);
            request.setAttribute(bookPageAttributeNameForFindByID, book2);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(urlPathForBookPage);
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            if (request.getParameter("welcomeNameBook") != null) {
                String welcomeName = request.getParameter("welcomeNameBook");
                String welcomeNameForSession = "Welcome " + welcomeName + ", On This Page You Can Add, Edit, Or Delete Authors.";
                session.setAttribute("welcomeNameForAuthorPage", welcomeNameForSession);
            } else if (request.getParameter("endSession") != null) {
                String endSessionWelcome = "Your Session Has Ended. Please Return to the Home Screen.";
                session.setAttribute("welcomeNameForAuthorPage", endSessionWelcome);
            }
            List<Book> book = bookService.findAll();
            request.setAttribute(bookPageAttributeName, book);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeRedirectURL(urlPathForBookPage));
            dispatcher.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

     @Override
    public void init() throws ServletException {
        // Get init params from web.xml
//        driverClass = getServletContext().getInitParameter("db.driver.class");
//        url = getServletContext().getInitParameter("db.url");
//        userName = getServletContext().getInitParameter("db.username");
//        password = getServletContext().getInitParameter("db.password");
        //dbJndiName = getServletContext().getInitParameter("db.jndi.name");
    }
}
