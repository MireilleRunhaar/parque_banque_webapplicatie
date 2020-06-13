const usernameField = document.getElementById("username");
const codeField = document.getElementById("securityCode");

usernameField.addEventListener("focusout", checkUserNameExists);
codeField.addEventListener("focusout", validateSecurityCode);


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
            // If username does not exist, show message
            if (json) {
                document.getElementById("unknownUsername").style.display = "inline";
            } else {
                document.getElementById("unknownUsername").style.display = "none";
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
        .then(response => respone.json)

        .then(json => {
            if(json) {
                document.getElementById("insecureCode").style.display = "none";
            } else {
                document.getElementById("insecureCode").style.display = "inline";
            }
        })
}