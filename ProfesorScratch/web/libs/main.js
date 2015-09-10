/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var counts = [0];
    var json;
    
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
                $.content = $('<input/>').attr({ type: 'text'});
                $.content.addClass("form-control");
                $.content.addClass("input-text-piece");
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
    
    function calcular_ancho_pieza(numInputs){
        var width = 0;
        var max = 11;
        
        width = Math.floor(max / numInputs);
        
        return width;
    }
    
    function buildPieces(f){
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
                
                $.input.addClass("col-md-" + calcular_ancho_pieza(pieces.inputs.length));
                pieza.append($.input);
            });
            
            make_draggable(pieza);
            pieza.css({"background-color":color_piezas()});
            $("#pieces-panel").append(pieza, null);
            
        });
    }
    
    function handleFileSelect(evt) {
        var f = evt.target.files[0];
        
        if (f.type.match('text.*')) {
            var reader = new FileReader();

            reader.onload = (function(theFile) {
                return function(e) {
                    buildPieces(e.target.result);
                };
            })(f);
            reader.readAsText(f);
        }
    }

    document.getElementById('files').addEventListener('change', handleFileSelect, false);
    
    /* Funcion para dar color a las piezas de forma aleatoria */
    var estilo_piezas = ["#CEEF72", "#FFFDA8", "#F0F8FF", "#FF9E9E"];
    var index = 0;
    function color_piezas(){
        index = (index + 1)%4;
        return estilo_piezas[index];
    }
});
