<%-- 
    Document   : login
    Author     : JavierOrdonezMartin
--%>

<%@page import="Entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="libs/log.js"></script>
         
         <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles2.css"/>
        
        <title>Login Page</title>
    </head>
    <body>
        <div id="container">
           <% 
            HttpSession miSesion = request.getSession(false);
            String log =(String) miSesion.getAttribute("Log");
            Alumno alu= (Alumno) miSesion.getAttribute("alumno");
            if(alu!=null){
           %>
           <a href="LoginAlumnoServlet">Cerrar Sesion</a>
           <% }else{ %>
            <div id="container-login">
                <h1>Login:</h1>
                <form method="post" action="LoginAlumnoServlet">
                    <label for="usuario">Usuario:</label><br>
                    <input type="text" name="usuario"><br>
                     
                    <label for="contraseña">Contraseña:</label><br>
                     <input type="password" name="password"><br>
                     
                     <input type="submit" value="Entrar">
                  </form>
            <%
                if(log=="error"){ 
           
            %>
           <div id="mensajeError">
               <strong>¡Error!</strong> Usuario o contraseña incorrectos
           </div>
            
            <% }%>
            </div>
            <% } %>
        </div>
    </body>
</html>
