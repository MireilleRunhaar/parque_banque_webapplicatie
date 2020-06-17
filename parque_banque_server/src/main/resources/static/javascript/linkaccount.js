const iban = document.getElementById("iban");
iban.addEventListener("focusout", checkIban);

function checkIban() {
let input = iban.value;

    const url = "http://localhost/check-Iban";
    let data = `iban=${input}`;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => response.json())
        .then(json => {
            if (json===false) {
             document.getElementById('IBAN_unknown').innerHTML = 'Rekeningnummer is niet bekend bij ons'
            }
        })
}



