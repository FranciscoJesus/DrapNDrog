/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    var counts = [0];
    var json;

    $(".dragIn").draggable({
        helper: 'clone',
        start: function() {
            counts[0]++;
        }
    });

    $(".dragOut").draggable({
        helper: 'original',
        containment: 'parent'
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
        accept: ".dragOut",
        drop: function(ev, ui) {
            $(ui.draggable).remove();
        }
    });

    $("#sortable").sortable();

    $("#finalizar").click(
            function(ev, ui) {
                
                var initElement = $("#sortable");
                
                json = mapDOM(initElement, true);
                console.log(json);

            });


    function mapDOM(element, json) {
        var treeObject = {};
        
        treeHTML(element, treeObject);

        return (json) ? JSON.stringify(treeObject) : treeObject;
    }

    function treeHTML(element, object) {
        object["type"] = element.nodeName;

        
        var list = $("#sortable").find(".piece ");
        for ( var i = 0 , len = list.length; i < len; i++){ 
           
            var piece = list[i];
            console.log(list[i]);
        
        
        }

        /*
         if (list != null) {
         
         if (list.length) {
         object["content"] = [];
         for (var i = 0; i < list.length; i++) {
         if (list[i].nodeType == 3) {
         object["content"].push(list[i].nodeValue);
         } else {
         object["content"].push({});
         treeHTML(list[i], object["content"][object["content"].length - 1]);
         }
         }
         }
         }
         if (element.attributes != null) {
         if (element.attributes.length) {
         object["attributes"] = {};
         for (var i = 0; i < element.attributes.length; i++) {
         object["attributes"][element.attributes[i].nodeName] = element.attributes[i].nodeValue;
         }
         }
         }
         */
        //console.log(object);
    }

});

