const bsnInput = document.getElementById("bsn");
const submitButton = document.getElementById("submit");
const zipcode = document.getElementById("zipcode");
const houseNumber = document.getElementById("number");

bsnInput.addEventListener("focusout", validateBsn);
houseNumber.addEventListener("focusout", fillInAddress);
zipcode.addEventListener("focusout", fillInAddress);

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

function fillInAddress() {
    let postcode = zipcode.value.split(" ").join("");
    let number = houseNumber.value;

    // If inputs do not match a standard postcode [1234AB] and a series of digits, return
    if (!(/^[0-9]{4}[A-Za-z]{2}$/).test(postcode) || !(/^[0-9]+$/).test(number)) return;

    let urlAddressLookup = `https://postcode.tech/api/v1/postcode?postcode=${postcode}&number=${number}`;

    fetch(urlAddressLookup, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer 30d415cb-dd44-42c0-9a4a-55aa7c213ba4'
        },
    })
        .then(response => response.json())

        .then(json => {
            document.getElementById("street").value = json.street;
            document.getElementById("city").value = json.city;
        })
}