<%-- 
    Document   : index
    Created on : Apr 18, 2016, 6:33:19 PM
    Author     : tliebl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User Page</h1>
          <sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
        </sec:authorize>
    </body>
</html>
