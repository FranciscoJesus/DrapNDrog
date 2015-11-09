<%-- 
    Document   : listadoSoluciones
    Created on : 23-sep-2015, 15:20:02
    Author     : Sobremesa
--%>

<%@page import="Entities.Asignatura"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="Entities.Solucion"%>
<%@page import="Entities.Alumno"%>
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

        <title>Soluciones propuestas</title>
        
    </head>
    <body>
        <% 
            HttpSession sesion = request.getSession(false);
            Profesor p = (Profesor)sesion.getAttribute("usuario");
            
            List<Solucion> soluciones = new ArrayList<Solucion>();
        
            int num = 1;
            
            if(p == null){
                sesion.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }else{
                soluciones = (ArrayList<Solucion>)request.getAttribute("soluciones");                
            }
        %>
        
        <%@include file="navegacion.jsp" %>
        
        <div class="container">

            <%@include file="header.jsp" %>

            <div id="alert_placeholder"></div>
            
            <div class="row">
                
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Descripci&oacute;n del Problema</h3>
                        </div>

                        <div id="description-panel" class="panel-body form-inline">
                            
                            <div id="titulo-asignatura-div" class="row">
                                <div class="form-group col-md-6">
                                    <label class="control-label" for="titulo">Titulo</label>
                                    <input id="titulo-input" type="text" class="form-control" placeholder="Titulo" readonly>
                                </div>
                                
                                <div class="form-group col-md-6">
                                    <label class="control-label" for="input-asignatura">Asignatura</label>
                                    <input id="input-asignatura" type="text" class="form-control" placeholder="Asignatura" readonly>
                                </div>
                            </div>
                            
                            <div id="enunciado-div" class="row">
                                <div class="col-md-12">
                                    <label class="control-label" for="enunciado">Enunciado</label>
                                    <textarea class="form-control" rows="2" id="enunciado" placeholder="Enunciado" readonly></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
                                    
            <% if(soluciones.size() > 0 ){ %>
            
                <table class="table table-striped">
                    <thead>
                        <th>#</th>
                        <th>Alumno</th>
                        <!-- <th>Enunciado</th> -->
                        <th>Nota</th>
                    </thead>
                
                <% for(Solucion s : soluciones ){ %>
                    <tr class="clickable-row" data-href="CorregirServlet?id=<%= s.id%>" id="<%= s.id%>">
                        <td><%= num %></td>
                        <td><%= s.nombre + " " +s.apellidos %></td>
                        <td><%= s.nota %></td>
                        <!-- <td><a href="SolucionServlet?id=<%= s.id%>" class="btn btn-default btn-sm">Solucion</a></td> -->
                    </tr>
                <% num++; %>
                <% } %>
                
                </table>
                
            <% } %>
        </div>
        <%
            Problema t = (Problema) request.getAttribute("problema");
            if (t != null) {
        %>                
                <script type="text/javascript">
                    $(function(){ $("#enunciado").val("<%= t.enunciado%>"); });
                    $(function(){ $("#titulo-input").val("<%= t.titulo%>"); });
                    $(function(){ $("#asignatura-input").val("<%= t.nombreAsignatura %>"); });
                </script>
        <%
            }
        %>
    </body>
</html>
