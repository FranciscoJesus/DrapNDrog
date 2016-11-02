<%-- 
    Document   : login
    Author     : Javier Ordoñez Martin
--%>

<%@page import="Entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <!-- Bootstrap -->
        <link href="libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="libs/bootstrap/js/bootstrap.min.js"></script>
        <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles2.css"/>

        <title>Drag and Build - Login!</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Desplegar navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Drag & Build</a>
            </div>
        </nav>
        <%
            HttpSession miSesion = request.getSession(false);
            String log = (String) miSesion.getAttribute("Log");
            Alumno alu = (Alumno) miSesion.getAttribute("alumno");
        %>


        <div class="container">
            <h3>Arrastra, mueve, construye y destruye!</h3>
            <% if (alu != null) {
                    response.sendRedirect("listaEnunciados.jsp");
                } else { %>
                
                <div class="login">
                    <form action="LoginAlumnoServlet" method="post">
                        <header>Iniciar Sesi&oacuten</header>
                        <label>Usuario</label>
                        <div class="form-group">
                            <input type="text" class="form-control" id="usuario" name="usuario">
                        </div>
                        <label>Contraseña</label>
                         <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password">
                         </div>
                        <button>Login</button>
                    </form>      
                <%
                    if (log == "error") {

                %>
                <div id="mensajeError">
                    <strong>¡Error!</strong> Usuario o contraseña incorrectos
                </div>

                <% }%>
            </div>
            <% }%>
            
        </div>
    </body>
</html>
