/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var counts = [0];
    $(".dragIn").draggable({
      
        start: function() { counts[0]++; }
    });

//    $(".dragOut").draggable({
//        helper:'original'
//    });
    
    $("#content-panel").droppable({
         accept:".dragIn",
         drop: function(ev,ui){
             var droppedItem = $(ui.draggable).clone();
             //droppedItem.addClass("item-"+counts[0]);                
             droppedItem.addClass("dragOut");
             droppedItem.removeClass("dragIn");
             $("#content-panel").append(droppedItem);
             make_draggable(droppedItem);
         }
     });
     
    $("#bin-panel").droppable({
        accept: ".dragOut",
        drop: function(ev, ui) {
            $(ui.draggable).remove();
        }
    });
     
    var zIndex = 0;
    function make_draggable(elements){	
            elements.draggable({
                
                    containment:'#content-panel',                    
                    start:function(e,ui){ ui.helper.css('z-index',++zIndex); }
//                    stop:function(e,ui){}
            });
    }
});
