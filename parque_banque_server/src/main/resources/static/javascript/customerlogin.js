let username = document.getElementById('userName');
let password = document.getElementById('password');
let username_empty = document.getElementById('username_empty');
let password_empty = document.getElementById('password_empty');

username.addEventListener('textInput', username_verify);
password.addEventListener('textInput', password_verify);

function validate() {
    if (username.value === '' || username.value == null) {
        username.style.border = "1px solid #FF0000";
        username_empty.style.display = "inline-block";
        username.focus();
        return false;
    }
    if (password.value === '' || password.value == null) {
        password.style.border = "1px solid #FF0000";
        password_empty.style.display = "inline-block";
        password.focus();
        return false;
    }
}

function username_verify() {
    if (username.value.length >= 1) {
        username.style.border = "none";
        username_empty.style.display = "none";
        return true;
    }
}

function password_verify() {
    if (password.value.length >= 1) {
        password.style.border = "none";
        password_empty.style.display = "none";
        return true;
    }
}