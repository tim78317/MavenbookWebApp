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
            <h1 id="titleHead">View Authors</h1>
            <form id="authForm" name="authForm" action="<%=response.encodeURL("AuthorController")%>" method="POST">
                <input type="text" id="welcomeName" name="welcomeName" placeholder="Please Enter Your Name"/>
                <button type="button" id="welcomeButton" class="btn btn-large btn-primary" name="welcomeButton">Continue To Authors List</button>
                <input type="submit" class="btn btn-large btn-success hidden" id="authorLink" name="authorLink" value="View Authors"/>
            </form>
        </div>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"></script>
        <script>
            $(function () {

                $('#welcomeButton').click(function () {
                    var test = $('#welcomeName').val();
                    if (test !== "") {
                        $('#authorLink').removeClass('hidden');
                        $('#welcomeName').hide(300);
                        $('#welcomeButton').hide(300);
                        $('#titleHead').html(test + ", Click View Authors to Continue");
                    } else {
                        $('#titleHead').html("Please Enter Your Name To Continue");
                    }

                });

            });
        </script>
    </body>
</html>
