<%-- 
    Document   : booksPage
    Created on : Apr 6, 2016, 10:29:45 PM
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
        <title>Books Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <!--<link href="resources/add/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
        <link href="resources/add/jquery-ui-1.11.4.custom/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/add/jquery-ui-1.11.4.custom/jquery-ui.structure.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/add/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" rel="stylesheet" type="text/css"/>

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <link href="resources/add/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="resources/style/muffin-table.css" rel="stylesheet"> 
    </head>
    <body>
        <jsp:include page="/navbarforauthor.jsp"/>

        <h4>${welcomeNameForAuthorPage}</h4>
        <form id="tableForm1" name="tableForm1" action="/controller/BookController" method="POST">
            <button type="sumbit" name="endSession" id="endSession">End Current Session</button>
            <div class="container-fluid">
                <!-- main content -->
                <div class="page-header">Books Table</div>
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
                                    <!-- <form class="form-search form-nomargin">
                                         <i id="tab_1_srem" class="icon-remove-circle tra icon-rpos"></i><input id="tab_1_svalue" class="search-query r6 minih" type="text"><button id="tab_1_search" type="button" class="btn btn-mini margin-left">Search</button>
                                     </form>-->
                                </div>
                            </div>		
                        </div>
                        <table id="" class="muffin table-bordered table-hover margin-bottom0 table">
                            <tr>
                                <th>Select</th>
                                <th>Book ID</th>
                                <th>Title</th>
                                <th>ISBN</th>
                                <th>Author</th>
                            </tr>
                            <c:forEach items="${books}" var="book">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="check1" id="check1" class="checkbox" value="${book.bookId}">

                                    </td>
                                    <td>${book.bookId}</td>
                                    <td>${book.title}</td>
                                    <td>${book.isbn}</td>
                                    <td>${book.authorId.authorName}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="table-foot muffin">	

                            <div class="pull-left">

                                <div class="btn-group">
                                    <button id="add" class="btn btn-primary btn-default" name="addButton" type="button" data-toggle="modal" data-target="#myModalAdd">Add</button>
                                </div>
                                <!-- Modal For Add-->
                                <div class="modal fade" id="myModalAdd" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">Create New Book</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input type="text" name="createBookTitle" placeholder="Book Title">
                                                <input type="text" name="createBookISBN" placeholder="Book ISBN">
                                                <input type="text" name="createAuthorID" placeholder="Author ID">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" id="createAuthorbtn" name="createBookbtn" class="btn btn-default">Create</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- End of Modal For Add-->
                                <div class="btn-group">
                                    <button id="getDetails" class="btn btn-warning btn-default" name="getDetails" type="submit">Edit</button>
                                    <button id="delete" name="delete" class="btn btn-danger btn-default" type="submit">Delete</button>
                                </div>	
                                <!-- Modal For Update-->
                                <div id="forHidden" class="hidden">
                                    <!-- Modal content-->
                                    <div class="modal-content" id="draggable">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Edit Author</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="text" name="idField" readonly="readonly" value="${findBookById.bookId}">
                                            <input type="text" id="bookTitleField" name="bookTitleField" value="${findBookById.title}">
                                            <input type="text" name="bookISBNField"  value="${findBookById.isbn}">
                                            <input type="text" name="authorIdField"  value="${findBookById.authorId.authorId}">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" id="updateAuthorbtn" name="updateBookbtn" class="btn btn-default">Update Book</button>    
                                            <button type="button" class="btn btn-default" id="hiddenClose">Close</button>
                                        </div>
                                    </div>
                                </div>
                                <!-- End of Modal For Update-->
                            </div>
                            <div style="text-align:right">
                                <select id="tab_1_rowsn" data-table-rowsn="yes" class="minih r6 margin-right tab_1_navi">
                                    <option value="1">1</option>
                                    <option value="10">10</option>
                                    <option value="50">50</option>
                                </select>
                                <span id="tab_1_navi" class="btn-group"></span>
                            </div>
                        </div>
                        <!-- end: table full -->	
                    </div>
                </div>
                <!-- row end -->
                <!-- end: main content -->     
            </div>
        </form>
        <!-- bootstrap -->
        <script src="resources/add/jquery-1.8.3.min.jsript"></script>
        <script src="resources/add/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/add/jquery-ui-1.11.4.custom/external/jquery/jquery.js" type="text/javascript"></script>     
        <script src="resources/add/jquery-ui-1.11.4.custom/jquery-ui.min.js" type="text/javascript"></script>
        <!-- muffin -->
        <script type="text/javascript" src=resources/js/lib_boxes.js"></script>
        <script type="text/javascript" src="resources/js/lib_tables.js"></script>
        <script type="text/javascript" src="resources/js/lib_live.js"></script>      
        <script type="text/javascript" src="resources/js/lib_main.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"></script>
        <script>

            $(function () {


                $("#draggable").draggable();

                enableDisableEditModal();

                function enableDisableEditModal() {
                    var test = $("#bookTitleField").val();
                    if (test !== "") {
                        $("#forHidden").removeClass('hidden');
                    }
                }

                $("#hiddenClose").click(function () {
                    $("#forHidden").addClass('hidden');
                });



            });

        </script>
    </body>
</html>

