<%-- 
    Document   : authorPage
    Created on : Feb 7, 2016, 3:02:17 PM
    Author     : timothy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author Page</title>
        <!-- Latest compiled and minified CSS -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <link href="application/add/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="application/add/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

        <link href="application/style/muffin-table.css" rel="stylesheet">
    </head>
    <body>
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
                            <a class="navbar-brand" href="homePage.jsp">Book Web App</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li class=""><a href="homePage.jsp">Home</a></li>
                                <li class="Active"><a href="#">Author Page</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>

            </div>
        </div>

        <form action="" method="">
        <div class="container-fluid">
            <!-- main content -->


            <div class="page-header">Example: Normal table with Result part</div>

            <!-- row -->
            <div class="row-fluid">
                <div class="span12">	 

                    <!-- table full -->
                    <div class="table-head muffin">		

                        <div id="box_tsea1_content" class="collapse">
                            <div class="table-search muffin">			
                                <select class="minih input-medium">
                                    <option>Today</option>
                                    <option>Last month</option>
                                    <option>All</option>
                                </select>			
                            </div>
                        </div>

                        <div class="table-search-opt">
                            <a class="pull-left" href="#self" data-toggle="collapse" data-target="#box_tsea1_content"><i id="box_tsea1_expand" class="icon-plus-sign"></i></a>
                            <div style="text-align:right">
                                <form class="form-search form-nomargin">
                                    <i id="tab_1_srem" class="icon-remove-circle tra icon-rpos"></i><input id="tab_1_svalue" class="search-query r6 minih" type="text"><button id="tab_1_search" type="button" class="btn btn-mini margin-left">Search</button>
                                </form>
                            </div>
                        </div>		

                    </div>

                    <table id="" class="muffin table-bordered table-hover margin-bottom0 table">
                        <tr>
                            <th>Select</th>
                            <th>Author ID</th>
                            <th>Author Name</th>
                            <th>Date Added</th>
                        </tr>
                        <c:forEach items="${authors}" var="auth">
                            <tr>
                            <td><input type="checkbox" name="" id="check1" class="checkbox" value="${auth.authorId}"></td>
                                <td>${auth.authorId}</td>
                                <td>${auth.authorName}</td>
                                <td>${auth.dateAdded}</td>
                            </tr>
                        </c:forEach>
                    </table>



                    <div class="table-foot muffin">	

                        <div class="pull-left">
                            <div class="btn-group">
                                <button id="tab_1_unselect" class="btn btn-mini" type="button"><i class="icon-remove-circle"></i></button>
                                <button id="selectall" class="btn btn-mini" type="button">All</button>
                            </div>

                            <div class="btn-group">
                                <button id="tab_1_add" class="btn btn-mini" type="button">Add</button>
                            </div>

                            <div class="btn-group">
                                <button id="tab_1_a1" class="btn btn-mini" type="button">In progress</button>
                                <button id="tab_1_a2" class="btn btn-mini" type="button">Freeze</button>
                            </div>			
                        </div>

                        <div style="text-align:right">
                            <select id="tab_1_rowsn" data-table-rowsn="yes" class="minih r6 margin-right tab_1_navi">
                                <option value="10">10</option>
                                <option value="50">50</option>
                                <option value="1">1</option>
                            </select>
                            <span id="tab_1_navi" class="btn-group"></span>
                        </div>

                    </div>

                    <!-- end: table full -->	

                </div>
            </div>
            <!-- row end -->




            <div class="page-header"></div>


            <!-- end: main content -->     
        </div>
        </form>
        <!-- bootstrap -->
        <script src="application/add/jquery-1.8.3.min.js"></script>
        <script src="application/add/bootstrap/js/bootstrap.js"></script>	


        <!-- muffin -->
        <script type="text/javascript" src="application/js/lib_boxes.js"></script>
        <script type="text/javascript" src="application/js/lib_tables.js"></script>	
        <script type="text/javascript" src="application/js/lib_live.js"></script>
        <script type="text/javascript" src="application/js/lib_main.js"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
     
    </body>
</html>
