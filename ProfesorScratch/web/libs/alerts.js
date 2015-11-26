/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Función javascript que nos genera una alerta con la librería Bootstrap
 * @param type Tipo de alerta que deseamos generar [warning|danger|success|info]
 * @param message Mensaje que deseamos mostrar en la alerta
 */
function throw_alert(type, message) {
    var span = "";
    
    switch (type) {
        case "warning":
            span = "<strong>Advertencia!</strong>";
            break;

        case "danger":
            span = "<strong>Error!</strong>";
            break;

        case "success":
            //span = "<strong>Success!</strong>";
            break;

        case "info":
            span = "<strong>Información</strong>";
            break;
    }

    $.alert = $("<div/>");
    $.alert.addClass('alert alert-' + type);
    $.alert.append("<a href='#' class='close fade' data-dismiss='alert' aria-label='close'>&times;</a>");
    $.alert.append(span + " " + message);
    
    if(type == "success" || type == "info")
        $.alert.fadeTo(2000, 500).slideUp(500, function(){ $.alert.alert('close'); });
    
    $("#alert_placeholder").append($.alert);
}