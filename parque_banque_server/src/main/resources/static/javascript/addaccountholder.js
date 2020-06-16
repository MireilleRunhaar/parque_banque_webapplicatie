const usernameField = document.getElementById("username");
const codeField = document.getElementById("securityCode");
const form = document.getElementById("form")


usernameField.addEventListener("focusout", checkUserNameExists);
usernameField.addEventListener("focusout", checkUsernameIsNew);
codeField.addEventListener("focusout", validateSecurityCode);

form.addEventListener("submit", function(event) {
    event.preventDefault();
    validateForm().then(validInput => {
        if (validInput) {
            saveAuthorisationIfNew().then(authorisation => {
                confirmWindow(authorisation);
            })
        } else {
            document.getElementById("unkownError").style.display = "inline";
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

async function confirmWindow(authorisation) {
    let username = authorisation.userName.bold();
    let code = authorisation.securityCode.bold();
    let iban = authorisation.iban.bold();

    let message = 'De klant met de gebruikersnaam ' + username + ' kan aan rekening ' + iban + ' worden toegevoegd. ' +
        'Geef de code ' + code + ' aan deze klant. Hij/zij heeft deze code nodig om gekoppeld te worden aan deze rekening.';
    Confirm.open({
        message: message,
        onok: () => {
            document.getElementById("backbutton").click();
        }
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

    let authorisation = await response.json();

    return authorisation;
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