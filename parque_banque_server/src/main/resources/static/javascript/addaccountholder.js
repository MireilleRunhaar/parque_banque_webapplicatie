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
            console.log("CHECK = TRUE? " + validInput);
            form.submit();
        } else {
            console.log("CHECK = FALSE? " + validInput);
        }
    })
})


async function validateForm() {
    let usernameExists = await checkUsernameExistsAsync(usernameField.value);
    let secureCode = await validateSecurityCodeAsync(codeField.value);
    let userIsNew = await checkUsernameIsNew(usernameField.value);

    let validInput = false;
    if (usernameExists && secureCode && userIsNew) {
        validInput = true;
    }

    return validInput;

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
                console.log("user is new")
                document.getElementById("userAlreadyAdded").style.display = "none";
            } else {
                console.log("user already added")
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