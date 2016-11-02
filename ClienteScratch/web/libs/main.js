/*
 * Document   : main.js
 * Author     : Javier Ordoñez Martin
 */

$(document).ready(function() {
    var counts = [0];
    
    $(".dragIn").draggable({
        helper: 'clone',
        start: function() {
            counts[0]++;
        }
    });

    $(".dragOut").draggable({
        helper: 'original',
       // containment: 'parent'
    });

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
        accept: ".dragOut, .dragOutE",
        drop: function(ev, ui) {
            $(ui.draggable).remove();
        }
    });

    $("#sortable").sortable();
    $("#finalizar").click(
         function confirmarEnviar(){  
             if (confirm('¿Seguro que desea enviar la respuesta?')){
                 enviar();
                
             }
        } );
    
   
   
    $("#finalizarSinCorregir").click(
         function confirmarGuardadoNoCorreguir(){  
             if (confirm('¿Seguro que desea Guardar la respuesta?')){
                 finalizarSinCorregir();
                
             }
        } );
     $("#salir").click(
         function confirmarGuardado(){  
             if (confirm('¿Seguro que desea Salir del problema?')){
                 window.location.replace("http://localhost:8080/ClienteScratch/listaEnunciados.jsp");
                
             }
        } );
        
            function enviar() {
                
                var json;
                
                json= mapDOM();
                 $.ajax({
                    type: 'POST',
                    url: "http://localhost:8080/ServidorMongo/API/Solucion/insertarSolucion",
                    data: json,
                    contentType: "application/json",
                    dataType: 'jsonp',
                    success: function (data, textStatus, jqXHR)
                    {
                        console.log(data);
                    }
                });
            }
             function finalizarSinCorregir() {
                
                var json;
                
                json= mapDOM();
                 $.ajax({
                    type: 'POST',
                    url: "http://localhost:8080/ServidorMongo/API/Borrador/insertarBorrador",
                    data: json,
                    contentType: "application/json",
                    dataType: 'jsonp',
                    success: function (data, textStatus, jqXHR)
                    {
                        console.log(data);
                    }
                });
            }

      function mapDOM(){
        var idProblema=$("#idProblema").val();
        var idAlumno=$("#idAlumno").val();
        var nombre=$("#nombre").val();
        var apellidos=$("#apellidos").val();;
        var list = $("#sortable").find(".piece ");
       var listaPiezas="{\"idAlumno\": \""+idAlumno+"\",\"idProblema\":\""+idProblema+"\",\"nombre\":\""+nombre+"\",\"apellidos\":\""+apellidos+"\",\"piezas\":[";
        if (list!==null) {    
            for (var i = 0, len = list.length; i < len; i++) {
                listaPiezas += "{ \"inputs\": [";
                
                for(var r= 0,  tam =list[i].children.length; r<tam ;r++){
                   
                    if(list[i].children[r].nodeName === "P"){
                        listaPiezas += "{\"type\": \"label\",\"value\": \""+list[i].children[r].innerHTML+"\"}";
                    }else if(list[i].children[r].nodeName === "INPUT") {
                            listaPiezas +="{\"type\": \"text\",\"value\": \""+list[i].children[r].value+"\"}";
                        }else if( list[i].children[r].nodeName === "SELECT") {
                            listaPiezas +="{\"type\": \"select\",\"value\": \""+list[i].children[r].value+"\"}";
                        }
                        
                    if(r+1 < tam){
                        listaPiezas+=",";
                    }
                }
                
                listaPiezas += "]}";
                if(i+1<len){
                        listaPiezas+=",";
                    }
            }
        }
        listaPiezas = listaPiezas + "]}";
        return listaPiezas;
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
                
                pieza.css({"background-color": estilo_piezas[(pieces.inputs.length) % 4]});
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
    /*
    function buildPiecesSolution(f) {
        $("#sortable").find(".piece").remove();
        buildPieces(f,"#sortable","dragOut",false);
    }
   */
   function buildPiecesSolution(f) {
        $("#sortable").find(".piece").remove();
        buildPieces(f,"#sortable","dragOutE",false);
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
                var index = 0;
                $.content = $('<select/>');
                f.value.forEach(function(option) {
                    if( index == f.opcion) $.content.append("<option selected>" + option + "</option>");
                    else $.content.append("<option>" + option + "</option>");
                    index++;
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

 
