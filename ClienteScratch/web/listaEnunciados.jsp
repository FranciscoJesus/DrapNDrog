<%-- 
    Document   : ListaEnunciados
    Author     : JavierOrdoñezMartin
--%>

<%@page import="java.util.List"%>
<%@page import="Entities.Problema"%>
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
                Alumno alu= (Alumno) miSesion.getAttribute("alumno");
                %>
        <div id="container">
            <div class="page-header">
                <h1>Drag & Drop</h1>
                <p class="lead">Arrastra, mueve, construye y destruye!</p>
            </div>
            <%
                if(alu!=null){
                    List<Problema> listProblemas = (List<Problema>) miSesion.getAttribute("listaProblemas");
                    for(Problema p : listProblemas){
            %>
                Lista de Problemas:
                    <%=p.id%>>
               <% } %>
              <% }else{ %>
                  
               Pasar a pagina de log
               <%}%>
               
        </div>
        
    </body>
</html>
