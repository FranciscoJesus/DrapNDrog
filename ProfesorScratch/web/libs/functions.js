/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function throw_alert(type, message) {
    var span = "";
    
    switch (type) {
        case "warning":
            span = "<strong>Warning!</strong>";
            break;

        case "danger":
            span = "<strong>Danger!</strong>";
            break;

        case "success":
            span = "<strong>Success!</strong>";
            break;

        case "info":
            span = "<strong>Info!</strong>";
            break;
    }

    $.alert = $("<div/>");
    $.alert.addClass('alert alert-' + type);
    $.alert.append("<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>");
    $.alert.append(span + " " + message);
    
    $("#alert_placeholder").append($.alert);
}