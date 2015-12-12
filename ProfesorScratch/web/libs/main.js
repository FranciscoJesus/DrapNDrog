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
        
        select: function(event, ui) {
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
            
        },
        
        beforeOpen: function(event, ui) {
            var $menu = ui.menu,
                    $target = ui.target,
                    extraData = ui.extraData; // passed when menu was opened by call to open()

            ui.menu.zIndex($(event.target).zIndex() + 1);

        }
    });
                
    var counts = [0];

    $("#content-panel").droppable({
        accept: ".dragIn, .dragOut",
        drop: function(ev, ui) {
            if (!ui.draggable.hasClass("dragOut")) {
                var droppedItem = $(ui.draggable).clone();
                droppedItem.addClass("item-" + counts[0]);
                droppedItem.addClass("dragOut");
                droppedItem.removeClass("dragIn");
                $("#sortable").append(droppedItem);
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
        var titulo = getTitulo(); //Obtenemos el titulo
        var asignatura = getAsignatura(); //Obtenemos la asignatura
        var enunciado = getEnunciado(); //Obtenemos el enunciado
        var solucion = getSolucion();   //Obtemenos la solución planteada
        var idProfesor = $("#idProfesor").val();
        
        if(jsonPiezas == "") jsonPiezas = "[]";
        
        /* @todo - Control de errores */
        if( enunciado != "" && titulo != "" && asignatura != "" && jsonPiezas != "[]" && solucion != "[]" && idProfesor != "" )
            json = '{\"enunciado\":\"' + enunciado + '", \"titulo\":\"' + titulo + '", \"nombreAsignatura\":\"' + asignatura + '", \"piezas\":' + jsonPiezas + ", \"solucion\":" + solucion + ", \"idProfesor\":\"" + idProfesor + "\"}";
        else json = null;
        
        if(json != null){
            $.ajax({
                type: 'POST',
                url: "http://localhost:8080/ServidorMongo/API/Problema/insertarProblema",
                data: json,
                contentType: "application/json",
                dataType: 'json',
                success: function(data, textStatus, jqXHR) {
                    if(data.id == null) throw_alert("danger","Ha habido un problema en la inserción");
                    else throw_alert("success","El problema se ha insertado correctamente");
                }
            });
        }else throw_alert("danger","Se requieren todos los datos para insertar un problema");
    });

    /**
     * Función que controla el botón de finalizar
     * Se encarga de recoger el enunciado, las piezas utilizadas y la solución planteada para enviarlas al servidor.
     */
    $("#actualizar").click(function(ev, ui) {

        var json;
        var titulo = getTitulo(); //Obtenemos el titulo
        var asignatura = getAsignatura(); //Obtenemos la asignatura
        var enunciado = getEnunciado(); //Obtenemos el enunciado
        var solucion = getSolucion();   //Obtemenos la solución planteada
        var idProfesor = $("#idProfesor").val();
        var idProblema = $("#idProblema").val();
        
        if(jsonPiezas == "") jsonPiezas = "[]";
        
        /* @todo - Control de errores */
        if( enunciado != "" && titulo != "" && asignatura != "" && jsonPiezas != "[]" && solucion != "[]" && idProfesor != "" )
            json = '{\"id\":\"' + idProblema + '",\"enunciado\":\"' + enunciado + '", \"titulo\":\"' + titulo + '", \"nombreAsignatura\":\"' + asignatura + '", \"piezas\":' + jsonPiezas + ", \"solucion\":" + solucion + ", \"idProfesor\":\"" + idProfesor + "\"}";
        else json = null;
        
        if(json != null){
            $.ajax({
                type: 'POST',
                url: "http://localhost:8080/ServidorMongo/API/Problema/insertarProblema",
                data: json,
                contentType: "application/json",
                dataType: 'json',
                success: function(data, textStatus, jqXHR) {
                    if(data.id == null) throw_alert("danger","Ha habido un problema en la actualización");
                    else throw_alert("success","El problema se ha actualizado correctamente");
                }
            });
        }else throw_alert("danger","Se requieren todos los datos para actualizar un problema");
    });
    
    function getTitulo(){
        return $('#titulo-input').val();
    }
    
    function getAsignatura(){
        return $('#select-asignatura').val();
    }
    
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


var estilo_piezas = ["#FF9E9E", "#CEEF72", "#FFFDA8", "#F0F8FF"];
var index = 0;

    function buildPieces(f,content, AuxClasses,draggable) {
        jsonPiezas = JSON.stringify(f);
        var o = f;
        
        if (o.length === undefined || o.length <= 0) {
            throw_alert("warning", "No se encuentran piezas");
            return;
        }

        o.forEach(function(pieces) {

            if (pieces.inputs !== undefined) {

                var pieza = $('<div/>', {
                    title: 'Piece',
                    class: "row piece ui-draggable ui-draggable-handle ui-sortable-handle"
                });

                if(AuxClasses != "") pieza.addClass(AuxClasses);
                
                pieces.inputs.forEach(function(entry) {
                    $.input = buildPieceField(entry);
                    pieza.append($.input);
                });
                
                pieza.css({"background-color": color_piezas( pieces.inputs.length )});
                if(draggable === true) make_draggable(pieza);
                $(content).append(pieza,null);

            } else {
                throw_alert("warning", "No se encuentran piezas en el fichero");
                return;
            }
        });
    }    

    /**
     * Función para construir las piezas en HTML
     * @param {type} f
     * @returns {undefined}
     */
    function buildPiecesList(f) {
        $("#pieces-panel").find(".piece").remove();
        buildPieces(f,"#pieces-panel","dragIn",true);
    }
    
    function buildPiecesSolution(f) {
        $("#sortable").find(".piece").remove();
        buildPieces(f,"#sortable","dragOut",false);
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
                if(f.value != "") $.content.val(f.value);
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
    function color_piezas(nInputs) {
        return estilo_piezas[(nInputs) % 4];
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