<%-- 
    Document   : login
    Author     : JavierOrdonezMartin
--%>

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
        
        <!-- Hojas de estilo -->
        <link rel="stylesheet" type="text/css" href="styles.css"/>
               
        <title>Iniciar sesión</title>
    </head>
    
    <body>
        <% if( ("0").equals(request.getAttribute("loginState")) ){ %>
            <script type="text/javascript">throw_alert("danger","Usuario o contraseña incorrectos");</script>
        <% } %>
        <div class="container">
            
            <div class="page-header">
                <h1>Drag & Drop</h1>
                <p class="lead">Arrastra, mueve, construye y destruye!</p>
            </div>
            
            <div id="alert_placeholder"></div>
            
            <div id="login-box" class="row">
                
                <div class="login-content col-xs-8 col-xs-offset-1 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4">
                    
                    <div class="panel panel-default panel-custom">

                        <div class="panel-heading">
                            <h3 class="panel-title">Iniciar sesión</h3>
                        </div>

                        <div id="login-panel" class="panel-body">
                            
                            <form action="LoginServlet" method="post">

                                <div class="form-group">
                                    <input type="text" class="form-control" id="usr" name="user" placeholder="Nombre de usuario">
                                </div>

                                <div class="form-group">
                                    <input type="password" class="form-control" id="pwd" name="pass" placeholder="Contraseña">
                                </div>

                                <div class="form-group button-submit">
                                    <input class="btn btn-default" type="submit" value="Entrar">
                                </div>
                                
                            </form>
                        </div>

                    </div>
                    
                </div>
                
            </div>
                
<!--                <div id="container-login" class="col-md-6 col-xs-6 col-sm-6 col-lg-6 col-xs-offset-3 col-sm-offset-3 col-md-offset-3 col-lg-offset-3">
                    
                    <h3>Iniciar sesión</h3>
                    
                    <div class="form-group">
                        <input type="text" class="form-control" id="usr" placeholder="Nombre de usuario">
                    </div>
                    
                    <div class="form-group">
                        <input type="password" class="form-control" id="pwd" placeholder="Contraseña">
                    </div>
                    
                    <div class="form-group button-submit">
                        <input class="btn btn-default" type="submit" value="Entrar">
                    </div>
                </div>
-->                
            
        </div>
    </body>
</html>
