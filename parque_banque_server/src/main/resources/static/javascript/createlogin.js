const submit = document.getElementById("done");

const passfield = document.getElementById("password");
const confirmpass = document.getElementById("confirmpassword");
const strengthBar = document.getElementById("strength");
const usernameField = document.getElementById("username");
const form = document.getElementById("form");

usernameField.addEventListener("focusout", validateUsername);
passfield.addEventListener("keyup", showStrength);
confirmpass.addEventListener("keyup", confirmPassword);

form.addEventListener("submit", function(event) {
    if (!(validateUsername() && confirmPassword())) {
        event.preventDefault();
    }
})

function validateUsername() {
    if (usernameField.value.length > 4) {
        document.getElementById("usernameTooShort").style.display = "none";
        return !checkUsernameExists();
    } else {
        document.getElementById("usernameTooShort").style.display = "inline";
        return false;
    }
}

function checkUsernameExists() {
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
            // If username is taken, show message and disable button.
            if (json) {
                document.getElementById("usernameTaken").style.display = "inline";
                submit.disabled = true;
                return true
            } else {
                document.getElementById("usernameTaken").style.display = "none";
                submit.disabled = false;
                return false;
            }
        })
}

function showStrength() {
    strengthBar.value = validateStrength();
}

function validateStrength() {
    let password = passfield.value;
    let strength = 0;

    if (password.length > 7) {
        strength += 20;
    }
    if (password.match(/[a-z]+/)) {
        strength += 20;
    }
    if (password.match(/[A-Z]+/)) {
        strength += 20;
    }
    if (password.match(/[0-9]+/)) {
        strength += 20;
    }
    if (password.match(/[@$!%*?&]+/)) {
        strength += 20;
    }

    submit.disabled = strength < 100;

    return strength;
}

function confirmPassword() {
    if (confirmpass.value.length < 1) {
        document.getElementById("passMismatch").style.display = "none";
        return;
    }

    let password = document.getElementById("password").value;

    if (password !== confirmpass.value) {
        document.getElementById("passMismatch").style.display = "inline";
        submit.disabled = true;
        return false;
    } else {
        document.getElementById("passMismatch").style.display = "none";
        submit.disabled = false;
        return true;
    }
}