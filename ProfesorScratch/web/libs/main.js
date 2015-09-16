/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var counts = [0];
    var jsonPiezas = "";
    var estilo_piezas = ["#CEEF72", "#FFFDA8", "#F0F8FF", "#FF9E9E"];
    var index = 0;
    
    $(".dragIn").draggable({
        helper:'clone',
        start: function(){ 
            counts[0]++;
        }
    });
    
    /**
     * Funcion que convierte un elementos como arrastable
     * 
     * @param {type} element
     * @returns {undefined}
     */
    function make_draggable(element){
        element.draggable({
            helper:'clone'
        });
    }
    
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
    
    function cleanPieces(f){
        $(".piece").remove();
    }
    
    /**
     * 
     * @param {type} f
     * @returns {f.value|String|jQuery}
     */
    function buildPieceField(f){

        $.content = "";
        switch(f.type){
            
            case "label":
                $.content = $('<p/>');
                $.content.text(f.value);
                break;
                
            case "text":
                /**
                 * $.wrapper = $('<div/>');
                 * $.wrapper.addClass("col-md-5");
                 */
                $.content = $('<input/>').attr({ type: 'text'});
                $.content.addClass("form-control");
                $.content.addClass("input-text-piece");
                
                //$.content = $.wrapper.append($.content);
                break;
                
            case "select":
                $.content = $('<select/>');
                f.value.forEach(function(option) {
                    $.content.append("<option>" + option + "</option>");
                });
                break;
        }
        
        //console.log($.content);
        return $.content;
    }
    
    /**
     * Función para construir las piezas en HTML
     * @param {type} f
     * @returns {undefined}
     */
    function buildPieces(f){
        jsonPiezas = f;
        var o = JSON.parse( f );
        
        cleanPieces(f);
        
        o.forEach(function(pieces) {
                        
            var pieza = $('<div/>', {
                //id: 'div',
                title: 'Piece',
                class: "row piece dragIn ui-draggable ui-draggable-handle"
            });
            
            pieces.inputs.forEach(function(entry){
                $.input = buildPieceField(entry);
                
                //$.input.addClass("col-md-" + calcular_ancho_pieza(pieces.inputs.length));
                pieza.append($.input);
            });
            
            make_draggable(pieza);
            pieza.css({"background-color":color_piezas()});
            $("#pieces-panel").append(pieza, null);
            
        });
    }
    
    /**
     * Funcion que procesa un fichero en tiempo de ejecución para generar las piezas
     * @param {type} evt
     * @returns {undefined}
     */
    function leerFichero(evt) {
        var f = evt.target.files[0];
        
        if (f.type.match('text.*')) {
            var reader = new FileReader();

            reader.onload = (function(theFile) {
                return function(e) {
                    buildPieces(e.target.result);   //Construir piezas
                };
            })(f);
            reader.readAsText(f);
        }
    }

    document.getElementById('files').addEventListener('change', leerFichero, false);
    
    /**
     * Función que recoge el enunciado y devuelve el contenido.
     * @returns {jQuery}
     */
    function getEnunciado(){
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
        
        /* @todo - Control de errores */
        json = '{\"enunciado\":\"' + enunciado + '", \"piezas\":' + jsonPiezas + ", \"solucion\":" + solucion + "}";
        var ob = JSON.parse(json);
        
        /*
         $.post("http://localhost:8080/ServidorMongo/API/Solucion/insertarSolucion", ob);
         */
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

                    if (r + 1 < tam) piezas += ",";
                }

                piezas += "]}";
                if (i + 1 < len) piezas += ",";
                
            }
        }
        piezas = piezas + "]";
        return piezas;
    }
    
    /**
     * 
     * @param {type} numInputs
     * @returns {Number}
     */
    function calcular_ancho_pieza(numInputs){
        var width = 0;
        var max = 11;
        
        width = Math.floor(max / numInputs);
        
        return width;
    }
        
    /**
     * 
     * @returns {String}
     */
    function color_piezas(){
        index = (index + 1)%4;
        return estilo_piezas[index];
    }
});
