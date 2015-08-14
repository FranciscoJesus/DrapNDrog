<%-- 
    Document   : index
    Created on : Aug 3, 2015, 2:01:04 PM
    Author     : FranciscoJesús
--%>
<%@page import="org.bson.Document"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Document elem = (Document) request.getAttribute("Nombre"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%=elem.getString("Nombre")%>!</h1>
    </body>
</html>
