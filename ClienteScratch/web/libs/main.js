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

    $(".dragOut").draggable({
        helper:'original',
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
        function (ev, ui){
           var items=$("#sortable").find('.piece').map(function(){
               
              
               return item;
                   
            
               
              
                 
              
              
            
           });
           json=JSON.stringify(items);
            console.log(items);
       }
    );
    
   
});
