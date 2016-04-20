<%-- 
    Document   : navbarforauthor
    Created on : Mar 9, 2016, 6:36:39 PM
    Author     : tliebl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <div class="navbar-wrapper">
            <div class="container">
                <nav class="navbar navbar-inverse navbar-static-top">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="<%=this.getServletContext().getContextPath()+ "/admin/homePage.jsp"%>">Book Web App</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li class=""><a href="<%=this.getServletContext().getContextPath()+ "/admin/homePage.jsp"%>">Home</a></li>
                                <li class="Active"><a href="#">Author Page</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
