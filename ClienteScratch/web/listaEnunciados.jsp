<%-- 
    Document   : ListaEnunciados
    Author     : JavierOrdoñezMartin
--%>

<%@page import="Entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Enunciados</title>
    </head>
    <body>
        <%
                 HttpSession miSesion = request.getSession(false);
                String log =(String) miSesion.getAttribute("Log");
                Alumno alu= (Alumno) miSesion.getAttribute("alumno");
                %>
        <div id="container">
            <%
                if(alu!=null){
            %>
                <div id="container-log">
               
                </div>
            
              <% }else{ %>
                  
               Pasar a pagina de log
               }%>
               
        </div>
        
    </body>
</html>
