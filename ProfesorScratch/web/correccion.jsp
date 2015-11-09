<%-- 
    Document   : main.jsp
    Created on : Sept 3, 2015
    Author     : Edgar Perez Ferrando
--%>

<%@page import="Entities.Solucion"%>
<%@page import="Entities.Input"%>
<%@page import="Entities.Pieza"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entities.Problema"%>
<%@page import="Entities.Asignatura"%>
<%@page import="Entities.Profesor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Drag and Drop - Build Your Question!</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">

        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="libs/bootstrap-filestyle/bootstrap-filestyle.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-contextmenu/jquery.ui-contextmenu.min.js"></script>
        <!-- <link href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css" type="text/css" rel="stylesheet" /> -->
        <link href="libs/jquery-ui-contextmenu/jquery-ui-contextmenu.css" type="text/css" rel="stylesheet" />
        
        <script type="text/javascript" src="libs/main.js"></script>
        <script type="text/javascript" src="libs/readFile.js"></script>

        <!-- Bootstrap -->
        <link href="libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="libs/bootstrap/js/bootstrap.min.js"></script>

        <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles.css"/>
        
    </head>
    <body>
        <%
            
            HttpSession sesion = request.getSession(false);
            Profesor p = (Profesor) sesion.getAttribute("usuario");

            if (p == null) {
                sesion.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            
        %>
        <!-- <input type="hidden" id="idProfesor" value="<% //= p.id%>"/> -->
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
                                        <input id="titulo-input" type="text" class="form-control" placeholder="Titulo">
                                </div>
                                
                                <div class="form-group col-md-6">
                                    <label class="control-label" for="select-asignatura">Asignatura</label>
                                    <select id="select-asignatura" class="form-control">
                                        <%
                                            /*
                                            List<Asignatura> asignaturas = (List<Asignatura>) sesion.getAttribute("asignaturas");
                                            if (asignaturas.size() > 0) {
                                                for (Asignatura a : asignaturas) {
                                            */        
                                        %><option><% //= a.nombre%></option><%
                                          //      }
                                          //  }
                                        %>
                                    </select>
                                </div>
                            </div>
                            
                            <div id="enunciado-div" class="row">
                                <div class="col-md-12">
                                    <label class="control-label" for="enunciado">Enunciado</label>
                                    <textarea class="form-control" rows="2" id="enunciado" placeholder="Enunciado"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
            
            <div class="row">

                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Builder panel</h3>
                        </div>

                        <div id="content-panel" class="panel-body grid">
                            <ul id="sortable">
                            </ul>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <button id="finalizar" class="btn btn-primary button-send" type="button" value="Entrar">Finalizar</button>
                        </div>
                    </div>

                </div>

                <div class="col-md-3" >

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Nota</h3>
                        </div>

                        <div id="pieces-panel-content" class="panel-body row">
                            <div id="nota-panel" class="col-md-10 col-sm-10 col-lg-10 col-xs-10 col-xs-offset-1 col-md-offset-1 col-sm-offset-1 col-lg-offset-1">
                                <input id="nota-input" type="text" class="form-control" placeholder="Nota">
                            </div>
                        </div>
                    </div>
                    
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Pieces panel</h3>
                        </div>

                        <div id="pieces-panel-content" class="panel-body row">
                            <div id="pieces-panel" class="col-md-10 col-sm-10 col-lg-10 col-xs-10 col-xs-offset-1 col-md-offset-1 col-sm-offset-1 col-lg-offset-1"></div>
                        </div>
                    </div>                    
                </div>
            </div>
        </div>
        
        <%
            Problema t = (Problema) request.getAttribute("problema");
            Solucion s = (Solucion) request.getAttribute("solucion");
            
            if (t != null) {
                
                String listaPiezas = t.generarJSONPiezas();
                //String solucion = s.generarJSONSolucion();
        %>                
                <script type="text/javascript">
                    $(function(){ $("#enunciado").val("<%= t.enunciado%>"); });
                    $(function(){ $("#titulo-input").val("<%= t.titulo%>"); });
                    $(function(){ $("#nota-input").val("<%= s.nota %>"); });
                    buildPiecesList(<%= listaPiezas %>);
                    //buildPiecesSolution(<% //= solucion %>);
                </script>

        <%
            }
        %>
    </body>
</html>
