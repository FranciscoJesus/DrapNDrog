<%-- 
    Document   : listadoProblemas
    Created on : 23-sep-2015, 15:20:02
    Author     : Sobremesa
--%>

<%@page import="java.util.List"%>
<%@page import="Entities.Problema"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entities.Profesor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="libs/bootstrap-filestyle/bootstrap-filestyle.min.js"> </script>
        <!-- <script type="text/javascript" src="libs/jquery-ui-contextmenu/jquery.ui-contextmenu.min.js"></script> -->
        <!-- <script type="text/javascript" src="libs/main.js"></script> -->
        <script type="text/javascript" src="libs/log.js"></script>

        <!-- Bootstrap -->
        <link href="libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="libs/bootstrap/js/bootstrap.min.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $(".clickable-row").click(function(elem) {
                        window.location = $(this).data('href');
                });
            });
        </script>
        
        <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles.css"/>

        <title>Listado de problemas</title>
        
    </head>
    <body>
        <% 
            HttpSession sesion = request.getSession(false);
            Profesor p = (Profesor)sesion.getAttribute("usuario");
            List<Problema> list = new ArrayList<Problema>();
            int num = 1;
            
            if(p == null){
                sesion.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }else{
                list = (ArrayList<Problema>)request.getAttribute("problemas");                
            }
        %>
        
        <%@include file="navegacion.jsp" %>
        
        <div class="container">

            <%@include file="header.jsp" %>

            <div id="alert_placeholder"></div>
            
            <% if(list.size() > 0 ){ %>
            
                <table class="table table-striped">
                    <thead>
                        <th>#</th>
                        <th>Titulo</th>
                        <th>Asignatura</th>
                        <th>Enunciado</th>
                        <th></th>
                    </thead>
                <% for(Problema t : list ){ %>
                    <!-- <tr class="clickable-row" data-href="ProblemaServlet?id=<%= t.id %>" id="<%= t.id %>"> -->
                        <td><%= num %></td>
                        <td><%= t.titulo %></td>
                        <td><%= t.nombreAsignatura %></td>
                        <td><%= t.enunciado %></td>
                        <td>
                            <a href="SolucionServlet?id=<%= t.id %>" class="btn btn-default btn-sm">Soluciones</a>
                            <a href="ProblemaServlet?id=<%= t.id %>" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-pencil"></span> Editar 
                            </a>
                                <a href="#" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-trash"></span> Eliminar
                            </a>
                        </td>
                    </tr>
                <!-- out.print(t.enunciado); -->
                <% num++; %>
                <% } %>
                
                </table>
                
            <% } %>
        </div>
        
    </body>
</html>
