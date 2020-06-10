const bsnInput = document.getElementById("bsn");
const submitButton = document.getElementById("submit");

bsnInput.addEventListener("focusout", validateBsn);


function validateBsn() {
    let input = bsnInput.value;

    const url = "http://localhost/bsn-controle";

    let data = `bsn=${input}`;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => response.json())

        .then(json => {
            // If bsn is invalid, show message and disable button
            if (json) {
                document.getElementById("validBsn").style.display = "none";
                submit.disabled = false;
            } else {
                document.getElementById("validBsn").style.display = "inline";
                submit.disabled = true;
            }
        })
}