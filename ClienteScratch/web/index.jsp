<%-- 
    Document   : index
    Created on : Aug 3, 2015, 2:07:08 PM
    Author     : FranciscoJesús
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <title>JSP Page</title>
        <style>
            div {
                width:350px;height:70px;padding:10px;border:1px solid #aaaaaa;overflow-y: scroll;
            }
        </style>
        <script>
           $(document).ready(function(){
               
               $("#drag1").draggable({helper:'clone'});
               
               $("div").droppable(
                       
                {
                    accept:"#drag1",
                    drop: function(ev,ui){
                        var droppedItem = $(ui.draggable).clone();
                        $(this).append(droppedItem);
                    }
                }
                
                );
           });
        </script>
    </head>
    <body>

        <p>Esto es una prueba de Drag N Drop</p>

        <div id="div1" ></div>
        <div id="div2" ></div>
        </br>
        <img id="drag1" src="ModelSakilaClient.png" width="336" height="69">
    </body>
</html>
