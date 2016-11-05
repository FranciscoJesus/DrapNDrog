<%--
    Document   : listaborradores
    Author     : Javier Ordoñez Martin
--%>

<%@page import="Entities.Problema"%>
<%@page import="Entities.Borrador"%>
<%@page import="java.util.List"%>
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
        <title>Lista Borradores</title>
    </head>
    <body>
        <%
            HttpSession miSesion = request.getSession(false);
            Alumno alu = (Alumno) miSesion.getAttribute("alumno");
            List<Problema> listaProblemas = (List<Problema>) miSesion.getAttribute("listaProblemas");

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
                <li><a href="listaEnunciados.jsp">Lista Enunciados</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= alu.nombre + " " + alu.apellidos%><span class="caret"></span></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="LogOutAlumnoServlet">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div class="container">
            <div class="page-header">
                <h3>Arrastra, mueve, construye y destruye!</h3>
            </div>
            <%                if (alu != null) {
                    List<Borrador> listaBorradores = (List<Borrador>) request.getAttribute("borradores");
                    if (listaBorradores != null) {
                        int i = 1;
            %>
            <table>
                <tr> 
                    <th>BORRADOR</th>
                    <th>ASIGNATURA</th>
                    <th>EJERCICIO</th>
                </tr>
                <%
                    for (Borrador b : listaBorradores) {
                        for (Problema p : listaProblemas) {
                            if (b.idProblema.equals(p.id)) {
                %>
                <tr>
                    <td>
                        <%=i%>
                    </td>
                    <td>
                        <%=p.nombreAsignatura%>
                    </td>
                    <td>
                        <a href="ProblemaBorradorAlumnoServlet?idBorrador=<%=b.id%>" ><%=p.titulo%></a>
                    </td>
                    <td>
                        <button type="button" onclick="location.href = 'BorrarBorradorAlumnoServlet?idBorrar=<%=b.id%>'" >Borrar</button>
                    </td>
                </tr>

                <%       }
                        }
                        i++;
                    }
                %>
            </table>
            <%
                    } else {
                        response.sendRedirect("index.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp");
                }
            %> 


        </div>
    </body>
</html>
