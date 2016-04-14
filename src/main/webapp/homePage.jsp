<%-- 
    Document   : homePage
    Created on : Feb 7, 2016, 3:02:00 PM
    Author     : timothy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <!-- Latest compiled and minified CSS -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"> 
    </head>
    <body>
        <jsp:include page="navbarforhome.jsp"/>
        <div class="container">
              <h1 id="titleHeadAuthor">View Authors</h1>
            <form id="authForm" name="authForm" action="<%=response.encodeURL("AuthorController")%>" method="POST">
                <input type="text" id="welcomeNameAuthor" name="welcomeNameAuthor" placeholder="Please Enter Your Name"/>
                <button type="button" id="welcomeButtonAuthor" class="btn btn-large btn-primary" name="welcomeButtonAuthor">Continue To Authors List</button>
                <input type="submit" class="btn btn-large btn-success hidden" id="authorLink" name="authorLink" value="View Authors"/>
            </form>
            <h1 id="titleHeadBook">View Books</h1>
            <form id="bookForm" name="bookForm" action="<%=response.encodeURL("BookController")%>" method="POST">
                <input type="text" id="welcomeNameBook" name="welcomeNameBook" placeholder="Please Enter Your Name"/>
                <button type="button" id="welcomeButtonBook" class="btn btn-large btn-primary" name="welcomeButtonBook">Continue To Books List</button>
                <input type="submit" class="btn btn-large btn-success hidden" id="bookLink" name="bookLink" value="View Books"/>
            </form>
        </div>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <script   src="https://code.jquery.com/jquery-2.2.3.min.js"   integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
        <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>-->
        <script   src="https://code.jquery.com/ui/1.12.0-rc.1/jquery-ui.min.js"   integrity="sha256-mFypf4R+nyQVTrc8dBd0DKddGB5AedThU73sLmLWdc0="   crossorigin="anonymous"></script>
        <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"></script>-->
        <script>
            $(function () {

                $('#welcomeButtonAuthor').click(function () {
                    var test = $('#welcomeNameAuthor').val();
                    if (test !== "") {
                        $('#authorLink').removeClass('hidden');
                        $('#welcomeNameAuthor').hide(300);
                        $('#welcomeButtonAuthor').hide(300);
                        $('#titleHeadAuthor').html(test + ", Click View Authors to Continue");
                    } else {
                        $('#titleHeadAuthor').html("Please Enter Your Name To Continue");
                    }

                });
                
                 $('#welcomeButtonBook').click(function () {
                    var test = $('#welcomeNameBook').val();
                    if (test !== "") {
                        $('#bookLink').removeClass('hidden');
                        $('#welcomeNameBook').hide(300);
                        $('#welcomeButtonBook').hide(300);
                        $('#titleHeadBook').html(test + ", Click View Books to Continue");
                    } else {
                        $('#titleHeadBook').html("Please Enter Your Name To Continue");
                    }

                });

            });
        </script>
    </body>
</html>
