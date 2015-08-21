/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var counts = [0];
    var currentParent;
    
    $(".dragIn").draggable({
        helper:'clone',
        start: function(){ 
            counts[0]++;
            currentParent = $(this).parent().attr('id');
            //alert("currentParent: " + currentParent);
        }
    });

    $(".dragOut").draggable({
        helper:'original',
        containment: 'parent'
    });
    $("#sortable-1").sortable({
       placeholder:"ui-state-highlight",
       helper:'clone',
       forceHelperSize: true
    });
    
    $("#sortable-1").droppable({
         accept:".dragIn , .dragOut",
         drop: function(ev,ui){

             var droppedItem = $(ui.draggable).clone();
             droppedItem.addClass("item-"+counts[0]);                
             droppedItem.addClass("dragOut");
             droppedItem.removeClass("dragIn");
             $("#sortable-1").append(droppedItem);
                       
         }
     });
    
    $("#bin-panel").droppable({
        accept: ".dragOut",
        drop: function(ev, ui) {
            $(ui.draggable).remove();
        }
    });
     
   
});