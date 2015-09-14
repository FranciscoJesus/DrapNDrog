<%-- 
    Document   : index
    Created on : Sept 3, 2015
    Author     : EdgarPerezFerrando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Drag and Drop - Build Your Question!</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
        <!-- Librerias jQuery online
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
        
        <script type="text/javascript" src="libs/jquery-2.1.4/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="libs/bootstrap-filestyle/bootstrap-filestyle.min.js"> </script>
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
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Enunciado</h3>
                        </div>

                        <div id="question-panel" class="panel-body">
                            <textarea class="form-control" rows="3" id="enunciado"></textarea>
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
                            <!-- <output id="list"></output> -->
                            <ul id="sortable">
                            </ul>
                        </div>
                        
                    </div>
                    <div class="row">
                        <div class="col-md-offset-5">
                            <button id="finalizar" type="button" class="btn btn-success button-send">Finalizar</button>
                        </div>
                    </div>
                        
                </div>
                
                <div class="col-md-3" >
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Pieces panel</h3>
                        </div>

                        <div id="pieces-panel" class="panel-body row">
                            <input type="file" id="files" class="filestyle" name="files"/>
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
                
    </body>
</html>
