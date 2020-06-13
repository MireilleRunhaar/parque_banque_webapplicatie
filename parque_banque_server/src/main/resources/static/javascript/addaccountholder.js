const usernameField = document.getElementById("username");
const codeField = document.getElementById("securityCode");

usernameField.addEventListener("focusout", checkUserNameExists);
codeField.addEventListener("focusout", validateSecurityCode);


function checkUserNameExists() {
    let input = usernameField.value;
    console.log("CHECKING USERNAME: " + input);

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
            } else {
                document.getElementById("unknownUsername").style.display = "inline";
            }
        })
}

function validateSecurityCode() {
    let codeInput = codeField.value;
    console.log("VALIDATING CODE: " + codeInput);

    const url = "http://localhost/veilige-code";

    let data = `securityCode=${codeInput}`;

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => response.json)

        .then(json => {
            console.log(json);
            console.log(json === true);
            if (json) {
                console.log("SAFE CODE");
                document.getElementById("insecureCode").style.display = "none";
            } else {
                console.log("UNSAFE CODE");
                document.getElementById("insecureCode").style.display = "inline";
            }
        })
}