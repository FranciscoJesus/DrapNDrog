/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var jsonPiezas = "";

$(document).ready(function() {
    var CLIPBOARD = "";

    $(document).contextmenu({
        delegate: ".dragOut",
        autoFocus: true,
        preventContextMenuForPopup: true,
        preventSelect: true,
        taphold: true,
        menu: [
            {title: "Remove", cmd: "remove", uiIcon: "ui-icon-trash"},
        ],
        // Handle menu selection to implement a fake-clipboard
        select: function(event, ui) {
            //console.log(event);
            //console.log(ui);
            var $target = ui.target;
            
            if( ! $target.hasClass("dragOut") ){
                $target = $target.parent();
            }
            
            if( ! $target.hasClass("dragOut") ){
                throw_alert("danger","No se puede eliminar la pieza seleccionada");
                return;
            }
            
            switch (ui.cmd) {
                case "remove":
                    CLIPBOARD = $target.remove();
                    throw_alert("info","Pieza eliminada");
                    break;
            }
            //alert("select " + ui.cmd + " on " + $target.text());
            // Optionally return false, to prevent closing the menu now
        },
        // Implement the beforeOpen callback to dynamically change the entries
        beforeOpen: function(event, ui) {
            var $menu = ui.menu,
                    $target = ui.target,
                    extraData = ui.extraData; // passed when menu was opened by call to open()

            ui.menu.zIndex($(event.target).zIndex() + 1);

/*
            $(document)
                    .contextmenu("setEntry", "copy", "Copy '" + $target.text() + "'")
                    .contextmenu("setEntry", "paste", "Paste" + (CLIPBOARD ? " '" + CLIPBOARD + "'" : ""))
                    .contextmenu("enableEntry", "paste", (CLIPBOARD !== ""));
*/

        }
    });
                
    var counts = [0];
    
    /*
     $(".dragIn").draggable({
     helper:'clone',
     start: function(){ 
     counts[0]++;
     }
     });
     */

    $("#content-panel").droppable({
        accept: ".dragIn, .dragOut",
        drop: function(ev, ui) {
            if (!ui.draggable.hasClass("dragOut")) {
                var droppedItem = $(ui.draggable).clone();
                droppedItem.addClass("item-" + counts[0]);
                droppedItem.addClass("dragOut");
                droppedItem.removeClass("dragIn");
                $("#sortable").append(droppedItem);
                make_removable(droppedItem);
            }
        }
    });

    $("#bin-panel").droppable({
        accept: ".dragOut",
        drop: function(ev, ui) {
            $(ui.draggable).remove();
        }
    });

    $("#sortable").sortable();

    /**
     * Función que recoge el enunciado y devuelve el contenido.
     * @returns {jQuery}
     */
    function getEnunciado() {
        return $('#enunciado').val();
    }

    /**
     * Función que controla el botón de finalizar
     * Se encarga de recoger el enunciado, las piezas utilizadas y la solución planteada para enviarlas al servidor.
     */
    $("#finalizar").click(function(ev, ui) {

        var json;
        var enunciado = getEnunciado(); //Obtenemos el enunciado
        var solucion = getSolucion();   //Obtemenos la solución planteada
        var idProfesor = $("#idProfesor").val();
        
        /* @todo - Control de errores */
        json = '{\"enunciado\":\"' + enunciado + '", \"piezas\":' + jsonPiezas + ", \"solucion\":" + solucion + ", \"idProfesor\":\"" + idProfesor + "\"}";
        //var ob = JSON.parse(json);
        console.log(json);

        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/ServidorMongo/API/Problema/insertarProblema",
            data: json,
            contentType: "application/json",
            dataType: 'jsonp',
            success: function(data, textStatus, jqXHR) {
                //console.log(data);
                throw_alert("success","El problema se ha enviado correctamente");
                //@todo - Reiniciar el interfaz
            },
            complete: function( jqXHR, textStatus ){
                throw_alert("success", textStatus);
            }
        });

    });

    function getSolucion() {
        var list = $("#sortable").find(".piece ");
        var piezas = "[";

        if (list != null) {
            for (var i = 0, len = list.length; i < len; i++) {
                piezas += "{\"inputs\":[";

                for (var r = 0, tam = list[i].children.length; r < tam; r++) {

                    if (list[i].children[r].nodeName == "P") {
                        piezas += "{\"type\":\"label\",\"value\":\"" + list[i].children[r].innerHTML + "\"}";
                    } else if (list[i].children[r].nodeName == "INPUT") {
                        piezas += "{\"type\":\"text\",\"value\": \"" + list[i].children[r].value + "\"}";
                    } else if (list[i].children[r].nodeName == "SELECT") {
                        piezas += "{\"type\":\"select\",\"value\":\"" + list[i].children[r].value + "\"}";
                    }
                    if (r + 1 < tam)
                        piezas += ",";
                }
                piezas += "]}";
                if (i + 1 < len)
                    piezas += ",";

            }
        }
        piezas = piezas + "]";
        return piezas;
    }
});


var estilo_piezas = ["#CEEF72", "#FFFDA8", "#F0F8FF", "#FF9E9E"];
var index = 0;


function make_removable(elem) {
    
}
    
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

    /**
     * Función para construir las piezas en HTML
     * @param {type} f
     * @returns {undefined}
     */
    function buildPieces(f) {
        jsonPiezas = JSON.stringify(f);
        var o = f;
        
        cleanPieces(f);

        //Comprobamos que tenemos un array
        if (o.length === undefined || o.length <= 0) {
            throw_alert("warning", "No se encuentran piezas en el fichero");
            return;
        }

        o.forEach(function(pieces) {

            // Comprobamos que tenemos inputs
            if (pieces.inputs !== undefined) {

                var pieza = $('<div/>', {
                    //id: 'div',
                    title: 'Piece',
                    class: "row piece dragIn ui-draggable ui-draggable-handle"
                });

                pieces.inputs.forEach(function(entry) {
                    $.input = buildPieceField(entry);
                    //$.input.addClass("col-md-" + calcular_ancho_pieza(pieces.inputs.length));
                    pieza.append($.input);
                });

                make_draggable(pieza);
                pieza.css({"background-color": color_piezas()});
                $("#pieces-panel").append(pieza, null);

            } else {
                throw_alert("warning", "No se encuentran piezas en el fichero");
                return;
            }

        });
    }
    
     /**
     * 
     * @param {type} f
     * @returns {f.value|String|jQuery}
     */
    function buildPieceField(f) {

        $.content = "";

        // Comprobamos que tenemos un tipo de pieza para poder identificarlo
        if (f.type === undefined) {
            throw_alert("warning", "No se encuentra el tipo de componente en una pieza");
            return;
        }

        switch (f.type) {

            case "label":
                $.content = $('<p/>');
                $.content.text(f.value);
                break;

            case "text":
                $.content = $('<input/>').attr({type: 'text'});
                $.content.addClass("form-control");
                $.content.addClass("input-text-piece");
                break;

            case "select":
                $.content = $('<select/>');
                f.value.forEach(function(option) {
                    $.content.append("<option>" + option + "</option>");
                });
                break;

            default:
                $.content = "";
                break;
        }
        return $.content;
    }
    
    function cleanPieces(f) {
        index = 0;
        $(".piece").remove();
    }
    
    /**
     * Funcion que convierte un elementos como arrastable
     * 
     * @param {type} element
     * @returns {undefined}
     */
    function make_draggable(element) {
        element.draggable({
            helper: 'clone'
        });
    }

    /**
     * 
     * @returns {String}
     */
    function color_piezas() {
        index = (index + 1) % 4;
        return estilo_piezas[index];
    }

    function isValidJson(json) {
        try {
            JSON.parse(json);
            return true;
        } catch (e) {
            return false;
        }
    }

    /**
     * 
     * @param {type} numInputs
     * @returns {Number}
     */
    function calcular_ancho_pieza(numInputs) {
        var width = 0;
        var max = 11;

        width = Math.floor(max / numInputs);

        return width;
    }