<%-- 
    Document   : index
    Created on : Aug 3, 2015, 2:07:08 PM
    Author     : FranciscoJesús
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Drag and Drop</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
        <!-- Librerias jQuery online
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
        
        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <!-- <script type="text/javascript" src="libs/jquery-ui-contextmenu/jquery.ui-contextmenu.min.js"></script> -->
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
                                <p>Pieza 1 <input type="text" name="pieza1"/></p>
                            </div>
                            
                            <div class="piece piece-2 dragIn">
                                <p>Pieza 2 <input type="text" name="pieza2"/></p>
                            </div>
                             <div class="piece piece-3 dragIn">
                                 <div>hola cabesa</div>
                                <p>Pieza 3 <input type="text" name="pieza3"/></p>
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
