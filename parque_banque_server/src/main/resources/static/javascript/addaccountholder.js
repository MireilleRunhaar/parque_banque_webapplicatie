const usernameField = document.getElementById("username");
const codeField = document.getElementById("securityCode");
const form = document.getElementById("form")
const usernameError = document.getElementById("unknownUsername");
const codeError = document.getElementById("insecureCode");

usernameField.addEventListener("focusout", checkUserNameExists);
codeField.addEventListener("focusout", validateSecurityCode);

// form.addEventListener("submit", function(event) {
//
//     if (!validateForm()) {
//         event.preventDefault();
//     }
// })


    // console.log("CHECKING BOTH FIELDS")
    //
    // let errors = [];
    //
    // console.log("USERNAME EXISTS? -> " + checkUserNameExists(returnValue(value)));
    // console.log("INSECURE CODE? -> " + validateSecurityCode());
    //
    // if (checkUserNameExists(callback(value))) {
    //     console.log("UNKNOWN USERNAME");
    //     errors.push(usernameError);
    // }
    //
    // if (!validateSecurityCode()) {
    //     console.log("INSECURE CODE");
    //     errors.push(codeError);
    // }
    //
    // if (errors.length > 0) {
    //     console.log("ERRORS EXIST");
    //     event.preventDefault();
    //     for (const value of errors) {
    //         value.style.display = "inline";
    //     }
    // }



async function validateForm() {
    let usernameExists = await  checkUsernameExistsAsync();
    let insecureCode = await validateSecurityCodeAsync();

    let validInput = false;

    usernameExists.then(data => {
        if (data) {
            validInput = true;
        }
    });
    if (!validInput) return false;
    insecureCode.then(data => {
        if (data) {
            validInput = true;
        }
    })
    return validInput;
}

async function checkUsernameExistsAsync() {
    let input = usernameField.value;

    const url = "http://localhost/username-controle";

    let data = `username=${input}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })

    return await response.json();
}

async function validateSecurityCodeAsync() {
    let codeInput = codeField.value;

    const url = "http://localhost/veilige-code";

    let data = `securityCode=${codeInput}`;

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });

    return await response.json();
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