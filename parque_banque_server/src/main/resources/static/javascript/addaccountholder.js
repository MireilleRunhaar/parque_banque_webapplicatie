const usernameField = document.getElementById("username");
const codeField = document.getElementById("securityCode");
const form = document.getElementById("form")
const usernameError = document.getElementById("unknownUsername");
const codeError = document.getElementById("insecureCode");

usernameField.addEventListener("focusout", checkUserNameExists);
codeField.addEventListener("focusout", validateSecurityCode);

form.addEventListener("submit", function(event) {

    validateForm().then(data => {
        if (data) {
            console.log("CHECK = " + data);

        } else {
            event.preventDefault();
        }
    })
})




async function validateForm() {
    let usernameExists = await checkUsernameExistsAsync(usernameField.value);
    let secureCode = await validateSecurityCodeAsync(codeField.value);

    let validInput = false;
    if (usernameExists && secureCode) {
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

    let output = await response.json();
    return output;
}

async function validateSecurityCodeAsync(codeInput) {
    const url = "http://localhost/veilige-code";

    let data = `securityCode=${codeInput}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });

    let output = await response.json();
    return output;
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
                console.log("JSON IS : " + json);
                return true;
            } else {
                document.getElementById("unknownUsername").style.display = "inline";
                return false;
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
                return true;
            } else {
                document.getElementById("insecureCode").style.display = "inline";
                return false;
            }
        })
}