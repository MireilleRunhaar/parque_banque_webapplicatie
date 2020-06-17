const usernameField = document.getElementById("username");
const codeField = document.getElementById("securityCode");
const form = document.getElementById("form");


usernameField.addEventListener("focusout", checkUserNameExists);
usernameField.addEventListener("focusout", checkUsernameIsNew);
codeField.addEventListener("focusout", validateSecurityCode);

// When submitted, validate input and save authorisation in database. If save is successful, show confirm window.
form.addEventListener("submit", function(event) {
    event.preventDefault();
    validateForm().then(validInput => {
        if (validInput) {
            saveAuthorisationIfNew().then(authorisation => {
                if (typeof authorisation !== "undefined") {
                    confirmWindow(authorisation);
                }
            })
        } else {
            errorWindow();
        }
    })
})


async function validateForm() {
    let usernameExists = await checkUsernameExistsAsync(usernameField.value);
    let secureCode = await validateSecurityCodeAsync(codeField.value);
    let userIsNew = await checkUsernameIsNewAsync(usernameField.value);

    let validInput = false;
    if (usernameExists && secureCode && userIsNew) {
        validInput = true;
    }

    return validInput;
}

function confirmWindow(authorisation) {
    let username = authorisation.userName.bold();
    let code = authorisation.securityCode.bold();
    let ibanInput = authorisation.iban.bold();

    let message = 'De klant met de gebruikersnaam ' + username + ' kan aan rekening ' + ibanInput + ' worden toegevoegd. ' +
        'Geef de code ' + code + ' aan deze klant. Hij/zij heeft deze code nodig om gekoppeld te worden aan deze rekening.';
    Confirm.open({
        message: message,
        onok: () => {
            document.getElementById("backbutton").click();
        }
    })
}

function errorWindow() {
    let errorMessage = "De invoer is ongeldig. Controleer de gegevens goed. " +
        "<br>De klant die u wilt toevoegen moet al een klant zijn van Parque Banque, en nog geen mederekeninghouder zijn. " +
        "De beveiligingscode mag niet uit dezelfde getallen bestaan of een oplopende of aflopende rij zijn."
    Confirm.open( {
        message: errorMessage
    })
}

async function saveAuthorisationIfNew() {
    let username = usernameField.value;
    let code = codeField.value;

    const url = "http://localhost/authorisatie-opslaan";

    let data = `username=${username}&code=${code}&iban=${iban}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })

    // If authorisation is a json object, return it. Else show error window
    try {
        let authorisation = await response.json();

        return authorisation;
    } catch {
        errorWindow();
    }
}

async function checkUsernameExistsAsync(input) {
    const url = "http://localhost/username-controle";

    let data = `username=${input}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });

    let validInput = await response.json();
    return validInput;
}

async function validateSecurityCodeAsync(input) {
    const url = "http://localhost/veilige-code";

    let data = `securityCode=${input}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });

    let validInput = await response.json();
    return validInput;
}

async function checkUsernameIsNewAsync(input) {
    const url = "http://localhost/nieuwe-rekeninghouder";

    let data = `username=${input}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
    let validInput = await response.json();
    return validInput;
}


function checkUserNameExists() {
    let input = usernameField.value;
    if (input.length < 5) return;

    const url = "http://localhost/username-controle";

    let data = `username=${input}`;

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
                document.getElementById("unknownUsername").style.display = "none";
            } else {
                document.getElementById("unknownUsername").style.display = "inline";
            }
        })
}

function checkUsernameIsNew() {
    let input = usernameField.value;
    if (input.length < 5) return;

    const url = "http://localhost/nieuwe-rekeninghouder";

    let data = `username=${input}`;

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
                document.getElementById("userAlreadyAdded").style.display = "none";
            } else {
                document.getElementById("userAlreadyAdded").style.display = "inline";
            }
        })
}

function validateSecurityCode() {
    let codeInput = codeField.value;
    if (codeInput.length < 5) return;

    const url = "http://localhost/veilige-code";

    let data = `securityCode=${codeInput}`;

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
                document.getElementById("insecureCode").style.display = "none";
            } else {
                document.getElementById("insecureCode").style.display = "inline";
            }
        })
}