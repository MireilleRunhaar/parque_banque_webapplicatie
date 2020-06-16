const formControlError = 'form-control error';
const formControlSucces = 'form-control succes';
const iban = document.getElementById("iban");
iban.addEventListener("focusout", checkIban);

function checkIban(input) {

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
            if (json) {
                setSuccesFor(iban)
            } else {
                setErrorFor(iban, "Iban is niet bij ons bekend")
            }
        })
}

    function setSuccesFor(inputField) {
        let formControl = inputField.parentElement;
        formControl.className = formControlSucces;
    }

    function setErrorFor(inputField, message) {
        let formControl = inputField.parentElement;
        let small = formControl.querySelector('small');
        small.innerText = message;
        formControl.className = formControlError;


    }
