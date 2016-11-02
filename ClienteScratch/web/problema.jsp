<%-- 
    Document   : index
    Author     : Javier Ordoñez Martín
--%>
<%@page import="Entities.Borrador"%>
<%@page import="Entities.Problema"%>
<%@page import="Entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Drag and Build</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">


        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

        <script type="text/javascript" src="libs/main.js"></script>

        <!-- Bootstrap -->
        <link href="libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="libs/bootstrap/js/bootstrap.min.js"></script>

        <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles.css"/>

    </head>
    <body>


        <%
            HttpSession miSesion = request.getSession(false);
            Alumno alu = (Alumno) miSesion.getAttribute("alumno");

            Problema problem = (Problema) request.getAttribute("problema");
            Borrador borrador = (Borrador) request.getAttribute("borrador");
            if (alu != null) {
                String solucion = "";
                if (borrador != null) {
                    solucion = borrador.generarJSONSBorrador();
                }
                if (problem != null) {
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
            <h3 class="lead">Arrastra, mueve, construye y destruye!</h3> 
            <input type="hidden" id="idProblema" value="<%= problem.id%>"/>
            <input type="hidden" id="idAlumno" value="<%= alu.id%>"/>
            <input type="hidden" id="nombre" value="<%= alu.nombre%>"/>
            <input type="hidden" id="apellidos" value="<%= alu.apellidos%>"/>


            <div class="row">

                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-enunciado">
                            <h1>Enunciado</h1>
                            <textarea class="panel-enunciado-ejercicio" id="enunciado" readonly> <%=problem.enunciado%> </textarea>
                        </div>
                        <div class="panel-heading">
                            <h3 class="panel-title">Builder panel</h3>
                        </div>

                        <div id="content-panel" class="panel-body grid">
                            <ul id="sortable">
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col-md-3" >
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Pieces panel</h3>
                        </div>
                        <%
                            String listaPiezas = problem.generarJSONPiezas();
                        %>

                        <div id="pieces-panel" class="panel-body">


                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Bin</h3>
                        </div>

                        <div id="bin-panel" class="panel-body">
                            <img id="bin-image" class="img-responsive img-thumbnail" src="images/bin-original.png"/>
                        </div>
                    </div>

                </div>

            </div>
            <button id="finalizar">Enviar</button>
            <button id="finalizarSinCorregir">Guardar</button> 
            <button id="salir">Salir</button> 
        </div>

        <script type="text/javascript"> //Script para representar la lista de piezas del problema. Y si hay piezas de un Borrador
            <% if (borrador != null) {%>
            buildPiecesSolution(<%= solucion%>);
            <%}%>
            buildPiecesList(<%= listaPiezas%>);
        </script>

        <%      } else {
                    response.sendRedirect("listaEnunciados.jsp");
                }
            } else {
                response.sendRedirect("index.jsp");

            }%>
    </body>

</html>
