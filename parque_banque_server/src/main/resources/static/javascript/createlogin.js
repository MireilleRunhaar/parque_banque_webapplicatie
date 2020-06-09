const submit = document.getElementById("done");
const passfield = document.getElementById("password");
const confirmpass = document.getElementById("confirmpassword");

passfield.addEventListener("keyup", validateStrength)
confirmpass.addEventListener("keyup", confirmPassword);

function validateStrength() {
    let password = passfield.value;
    let strengthBar = document.getElementById("strength");
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
    strengthBar.value = strength;
}

function confirmPassword() {
    if (confirmpass.value.length < 1) return;

    let password = document.getElementById("password").value;

    if (password !== confirmpass.value) {
        document.getElementById("passMismatch").style.display = "inline";
        submit.disabled = true;
    } else {
        document.getElementById("passMismatch").style.display = "none";
        submit.disabled = false;
    }
}