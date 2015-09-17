/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    
   $("#enviar").click(
            function() {
                var usu=$('input:text[name=usrname]').val();
                var pass=$('input:password[name=usrpass]').val(); 
                var rol="2";
                var json="{\"usuario\":\""+usu+"\",\"password\":\""+pass+"\",\"rol\":"+rol+"}"; 
                console.log(json);
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8080/ServidorMongo/API/Usuario/Login",
                    data: json,
                    contentType: "application/json",
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR)
                    {
                       // console.log(data);
                       // console.log("hola");
                         window.location="http://www.google.com";
                    }
                });
            
            });
    
});