<%-- 
    Document   : index
    Author     : JavierOrdoñezMartín
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Drag and Drop</title>
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
        
        <div class="container">
            <div class="page-header">
                <h1>Drag & Drop</h1>
                <p class="lead">Arrastra, mueve, construye y destruye!</p>
            </div>
            
            <div class="row">
                
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-enunciado">
                            <h1>Enunciado</h1>
                            <textarea class="panel-enunciado-ejercicio" id="enunciado" readonly> Hola!</textarea>
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

                        <div id="pieces-panel" class="panel-body">
                            <div class="piece piece-1 dragIn">
                                <p clase="cold-md-5">Pieza 1 </p>
                                    <input type="text" clase="form-control input-text-piece col-md-3" type="text"/>
                            </div>
                            
                            <div class="piece piece-2 dragIn">
                                <select class="col-md-11">
                                        <option>op1</option>
                                        <option>op2</option>
                                        <option>op3</option>
                                        </select>
                            </div>
                             <div class="piece piece-3 dragIn">
                                 
                                <p clase="cold-md-5">Pieza 3 </p>
                                    <input type="text" clase="form-control input-text-piece col-md-3" type="text"/>
                                 <p clase="cold-md-5">ok </p>   
                            </div>
                            
                            <!-- <img id="drag1" class="img-responsive" src="ModelSakilaClient.png"  /> -->
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
        </div>
         <button id="finalizar">Finalizar</button> 
    </body>
</html>
