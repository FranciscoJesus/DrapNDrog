/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Javier Ordoñez Martín
 */

$(document).ready(function () {
    var counts = [0];

    $(".dragIn").draggable({
        helper: 'clone',
        start: function () {
            counts[0]++;
        }
    });

    $(".dragOut").draggable({
        helper: 'original',
        containment: 'parent'
    });

    $("#content-panel").droppable({
        accept: ".dragIn, .dragOut",
        drop: function (ev, ui) {
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
        drop: function (ev, ui) {
            $(ui.draggable).remove();
        }
    });

    $("#sortable").sortable();


    $("#finalizar").click(
            function (ev, ui) {

                var json;

                json = mapDOM();
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
            });

    function mapDOM() {
        var list = $("#sortable").find(".piece ");
        var listaPiezas = "{\"idAlumno\": \"Waticontella29\",\"idProblema\":\"55eecca002e2d107e0a53cff\", \"piezas\": [";

        if (list != null) {
            for (var i = 0, len = list.length; i < len; i++) {
                listaPiezas += "{ \"inputs\": [";

                for (var r = 0, tam = list[i].children.length; r < tam; r++) {

                    if (list[i].children[r].nodeName == "P") {
                        listaPiezas += "{\"type\": \"label\",\"value\": \"" + list[i].children[r].innerHTML + "\"}";
                    } else if (list[i].children[r].nodeName == "INPUT") {
                        listaPiezas += "{\"type\": \"text\",\"value\": \"" + list[i].children[r].value + "\"}";
                    } else if (list[i].children[r].nodeName == "SELECT") {
                        listaPiezas += "{\"type\": \"select\",\"value\": \"" + list[i].children[r].value + "\"}"
                    }

                    if (r + 1 < tam) {
                        listaPiezas += ",";
                    }
                }

                listaPiezas += "]}"
                if (i + 1 < len) {
                    listaPiezas += ",";
                }
            }
        }
        listaPiezas = listaPiezas + "]}";
        return listaPiezas;
    }


});

