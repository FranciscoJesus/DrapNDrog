/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

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
                    var json = e.target.result;

                    if (isValidJson(json))
                        buildPieces(JSON.parse(json));   //Construir piezas
                    else
                        throw_alert("danger", "El fichero <strong>" + f.name + "</strong> introducido no tiene un formato válido.");
                };
            })(f);
            reader.readAsText(f);
        } else
            throw_alert("danger", "El fichero <strong>" + f.name + "</strong> introducido no es un fichero válido");
    }

    document.getElementById('files').addEventListener('change', leerFichero, false);

});