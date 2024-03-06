//BTN CARICA DISATTIVO/ATTIVO FOTO
function checkFile() {
    var fileInput = document.getElementById('foto');
    var caricaImmagineButton = document.getElementById('caricaImmagine');

    // Abilita il pulsante solo se è stato selezionato un file
    caricaImmagineButton.disabled = !fileInput.files || fileInput.files.length === 0;
}
//BTN CARICA DISATTIVO/ATTIVO RITRATTO
function checkFile2() {
    var fileInput = document.getElementById('ritratto');
    var caricaImmagineButton = document.getElementById('caricaRitratto');

    // Abilita il pulsante solo se è stato selezionato un file
    caricaImmagineButton.disabled = !fileInput.files || fileInput.files.length === 0;
}

//TAB ATTIVA di default
    $(document).ready(function () {
        // Imposta manualmente la classe "active" sulla prima tab al caricamento della pagina
        $('.nav-link:first').addClass('active');
        $('.tab-pane:first').addClass('show active');
    });
    
//CERCA in tutte le colonne
	function filterTable() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            var visible = false;

            // Loop through all columns (except the first one with checkboxes)
            for (var j = 1; j < tr[i].cells.length; j++) {
                td = tr[i].getElementsByTagName("td")[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        visible = true;
                    }
                }
            }

            if (visible) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
    
    //dropdown filter stato

    function filterTable(selectedStato) {
        var table, rows, statoColumn, statoText;

        table = document.getElementById("myTable");
        rows = table.getElementsByTagName("tr");

        // Loop through all table rows
        for (var i = 0; i < rows.length; i++) {
            // Get the stato column cell of the current row
            statoColumn = rows[i].getElementsByTagName("td")[6]; // 6 è l'indice della colonna "Stato"
            if (statoColumn) {
                statoText = statoColumn.textContent || statoColumn.innerText;

                // Check if the selectedStato is "Tutti" or matches the stato of the current row
                if (selectedStato === "Tutti" || statoText.trim() === selectedStato) {
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    }
    
//DROPDOWN FILTER STATO mycand
	function filterTableMycandidature(selectedStato) {
	        var table, rows, statoColumn, statoText;
	
	        table = document.querySelector(".mycand-table");
	        rows = table.getElementsByTagName("tr");
	
	        // Loop through all table rows
	        for (var i = 0; i < rows.length; i++) {
	            // Get the stato column cell of the current row
	            statoColumn = rows[i].getElementsByTagName("td")[3]; // 3 è l'indice della colonna "Stato"
	            if (statoColumn) {
	                statoText = statoColumn.textContent || statoColumn.innerText;
	
	                // Check if the selectedStato is "Tutti" or matches the stato of the current row
	                if (selectedStato === "Tutti" || statoText.trim() === selectedStato) {
	                    rows[i].style.display = "";
	                } else {
	                    rows[i].style.display = "none";
	                }
	            }
	        }
	    }

        
        //SELEZIONA TUTTO
	document.getElementById('selezionaTutto').addEventListener('change', function () {
        var righe = document.querySelectorAll('input[name="selezionaRiga"]');
        for (var i = 0; i < righe.length; i++) {
            righe[i].checked = this.checked;
        }
    });
    
    //FILM
    //CERCA in tutte le colonne
	function filterTableF() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInputf");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTablef");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            var visible = false;

            // Loop through all columns (except the first one with checkboxes)
            for (var j = 1; j < tr[i].cells.length; j++) {
                td = tr[i].getElementsByTagName("td")[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        visible = true;
                    }
                }
            }

            if (visible) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
        
        //SELEZIONA TUTTO
	document.getElementById('selezionaTuttof').addEventListener('change', function () {
        var righe = document.querySelectorAll('input[name="selezionaRiga"]');
        for (var i = 0; i < righe.length; i++) {
            righe[i].checked = this.checked;
        }
    });
    
     //attori
    //CERCA in tutte le colonne
	function filterTableA() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInputa");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTablea");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            var visible = false;

            // Loop through all columns (except the first one with checkboxes)
            for (var j = 1; j < tr[i].cells.length; j++) {
                td = tr[i].getElementsByTagName("td")[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        visible = true;
                    }
                }
            }

            if (visible) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
        
        //SELEZIONA TUTTO
	document.getElementById('selezionaTuttoa').addEventListener('change', function () {
        var righe = document.querySelectorAll('input[name="selezionaRiga"]');
        for (var i = 0; i < righe.length; i++) {
            righe[i].checked = this.checked;
        }
    });
    
	
     //UTENZE
    //CERCA in tutte le colonne
	function filterTableU() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInputu");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTableu");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            var visible = false;

            // Loop through all columns (except the first one with checkboxes)
            for (var j = 1; j < tr[i].cells.length; j++) {
                td = tr[i].getElementsByTagName("td")[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        visible = true;
                    }
                }
            }

            if (visible) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
        
        //SELEZIONA TUTTO
	document.getElementById('selezionaTuttou').addEventListener('change', function () {
        var righe = document.querySelectorAll('input[name="selezionaRiga"]');
        for (var i = 0; i < righe.length; i++) {
            righe[i].checked = this.checked;
        }
    });
    

