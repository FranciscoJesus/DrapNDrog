<%-- 
    Document   : ListaEnunciados
    Author     : Javier Ordoñez Martin
--%>

<%@page import="java.util.List"%>
<%@page import="Entities.Problema"%>
<%@page import="Entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--jQuery-->
        <script src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <!-- Bootstrap -->
        <link href="libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="libs/bootstrap/js/bootstrap.min.js"></script>
        <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles.css"/>
        
        
        <title>Lista Enunciados</title>
    </head>
    <body>

        <%
            HttpSession miSesion = request.getSession(false);
            Alumno alu = (Alumno) miSesion.getAttribute("alumno");
        %>
        <nav class="navbar navbar-default" role="navigation">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Desplegar navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="listaEnunciados.jsp">Drag & Build</a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="BorradoresAlumnoServlet">Lista Borradores</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= alu.nombre + " " + alu.apellidos%><span class="caret"></span></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="LogOutAlumnoServlet">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div class="container">
            <h3>Arrastra, mueve, construye y destruye!</h3>
            <%
                if (alu != null) {
                    List<Problema> listProblemas = (List<Problema>) miSesion.getAttribute("listaProblemas");

                    if (listProblemas.size() < 0) {
            %>
            NO TIENES PROBLEMAS ASIGNADOS
            <% } else { %>
            <table>
                <tr> 
                    <th>Asignatura</th>
                    <th>Lista de Problemas</th>
                </tr>
                <% for (Problema p : listProblemas) {%>
                <tr> 
                    <td><%=p.nombreAsignatura%></td>
                    <td><%=p.titulo%> </td>
                    <td><button type="button" onclick="location.href = 'ProblemaAlumnoServlet?idProblema=<%=p.id%>'" >Solucionar</button></td>
                </tr>
                <%} %>
            </table>
            <%       }
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>
        </div>
    </body>
</html>
