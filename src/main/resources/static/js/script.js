function checkFile() {
    var fileInput = document.getElementById('foto');
    var caricaImmagineButton = document.getElementById('caricaImmagine');

    // Abilita il pulsante solo se è stato selezionato un file
    caricaImmagineButton.disabled = !fileInput.files || fileInput.files.length === 0;
}