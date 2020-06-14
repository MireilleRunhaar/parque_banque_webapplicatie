function validate() {
    document.getElementById('IBAN_error').innerHTML = ''
    document.getElementById('sec_error').innerHTML = ''
    var ibannr = document.getElementById("iban").value;
    var secCode  = document.getElementById('securityCode').value;
    var ibanRegex = /^NL\d{2}[A-Z]{4}0\d{9}$/;
    var secRegex = /^\d{5}$/;
    var ibanResult = ibanRegex.test(ibannr);
    var secResult = secRegex.test(secCode);
    console.log(secCode, secResult)
    if (ibanResult === true && secResult === true){//ajax code en iban matchen
        document.form.submit();
    } else if (ibanResult === false && secResult === true) {
        document.getElementById('IBAN_error').innerHTML = 'het ibannummer bestaat uit 2 letter, 2 cijfers, 4 letters en 10 cijfers'
    } else if (ibanResult === true && secResult === false) {
        document.getElementById('sec_error').innerHTML = 'de beveiligingscode bestaat uit 5 cijfers'
    } else {
        document.getElementById('IBAN_error').innerHTML = 'het ibannummer bestaat uit 2 letter, 2 cijfers, 4 letters en 10 cijfers'
        document.getElementById('sec_error').innerHTML =  'de beveiligingscode bestaat uit 5 cijfers'
    }
}
