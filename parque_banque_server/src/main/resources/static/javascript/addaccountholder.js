let usernameField = document.getElementById("username");
let codeField = document.getElementById("securityCode");
let subButton = document.getElementById("submitbutton");
const form = document.getElementById("form")
const usernameError = document.getElementById("unknownUsername");
const codeError = document.getElementById("insecureCode");

usernameField.addEventListener("focusout", checkUserNameExists);
codeField.addEventListener("focusout", validateSecurityCode);

function returnValue(value) {
    return value;
}

form.addEventListener("submit", function(event) {
    console.log("CHECKING BOTH FIELDS")

    let errors = [];


    console.log("USERNAME EXISTS? -> " + checkUserNameExists(returnValue(value)));
    console.log("INSECURE CODE? -> " + validateSecurityCode());

    if (checkUserNameExists(callback(value))) {
        console.log("UNKNOWN USERNAME");
        errors.push(usernameError);
    }

    if (!validateSecurityCode()) {
        console.log("INSECURE CODE");
        errors.push(codeError);
    }

    if (errors.length > 0) {
        console.log("ERRORS EXIST");
        event.preventDefault();
        for (const value of errors) {
            value.style.display = "inline";
        }
    }

    // if (validateForm() > 0) {
    //     console.log("***** UNKNOWN USERNAME")
    //     event.preventDefault();
    //     document.getElementById("unknownUsername").style.display = "inline";
    // }

    // if (validateSecurityCode()) {
    //     console.log("***** INSECURE CODE")
    //     event.preventDefault();
    //     document.getElementById("insecureCode").style.display = "inline";
    // }
    // if (checkUserNameExists()) {
    //     if (validateSecurityCode()) {
    //         console.log("ALL OK!");
    //     } else {
    //         console.log("***** INSECURE CODE")
    //         event.preventDefault();
    //         document.getElementById("insecureCode").style.display = "inline";
    //     }
    // } else {
    //     console.log("***** UNKNOWN USERNAME")
    //     event.preventDefault();
    //     document.getElementById("unknownUsername").style.display = "inline";
    // }
})


// async function checkUserNameExistsAsync(callback) {
//     let input = usernameField.value;
//
//     const url = "http://localhost/username-controle";
//
//     let body = `username=${input}`;
//
//     let response = await fetch(url, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded'
//         },
//         body: body
//     });
//
//     let json =  await response.json();
//     // return !!json;
//     console.log("### Async json: " + json);
//     // callback(json);
//     return json;
//
// }

function checkUserNameExists(callback) {
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
            // If username does not exist, show message
            if (json) {
                document.getElementById("unknownUsername").style.display = "none";
                console.log("JSON IS : " + json);
                callback(true);
                return true;
            } else {
                document.getElementById("unknownUsername").style.display = "inline";
                callback(false);
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