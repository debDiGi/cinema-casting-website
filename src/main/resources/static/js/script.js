//BTN CARICA DISATTIVO/ATTIVO
function checkFile() {
    var fileInput = document.getElementById('foto');
    var caricaImmagineButton = document.getElementById('caricaImmagine');

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
    

	       // Salva la tab attiva prima del submit
    document.getElementById('myTabs').addEventListener('click', function (event) {
        localStorage.setItem('activeTab', event.target.getAttribute('href'));
    });

    // Ripristina la tab attiva dopo il ricaricamento della pagina
    window.onload = function () {
        var activeTab = localStorage.getItem('activeTab');
        if (activeTab) {
            document.querySelector('[href="' + activeTab + '"]').click();
        }
    };

    // Rimuovi l'informazione quando l'utente lascia la pagina delle tabs
    window.addEventListener('beforeunload', function () {
        localStorage.removeItem('activeTab');
    });
	