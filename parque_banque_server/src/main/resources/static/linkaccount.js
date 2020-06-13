function validate() {
    var ibannr = document.getElementById("iban").value;
    var ibanRegex = /^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$/i;
    var ibanResult = ibanRegex.test(ibannr);
    console.log(ibanResult);
    if (ibanResult == true){
        document.getElementById('form').submit()
    }


}

// $(function () {
//     $('button').on('click', function () {
//         validate()
//     })
//
// })





// let username = document.getElementById('userName');
// let password = document.getElementById('password');
// const submit = document.getElementById("done");
//
// //Validation not empty fields, plus removal of warning
// username.addEventListener('focusout', validateUsername);
// password.addEventListener('focusout', validatePassword);
// username.addEventListener('textInput', usernameVerifyInput);
// password.addEventListener('textInput', passwordVerifyInput);
//
// //Validation login data (if you hover above submit button)
// submit.addEventListener('click', validateLoginData);
//
// function validateUsername() {
//     if (username.value === '' || username.value == null) {
//         username.style.border = "1px solid #FF0000";
//         document.getElementById("usernameEmpty").style.display = "inline-block";
//         return false;
//     }
// }
//
// function validatePassword() {
//     if (password.value === '' || password.value == null) {
//         password.style.border = "1px solid #FF0000";
//         document.getElementById("passwordEmpty").style.display = "inline-block";
//         return false;
//     }
// }
//
// function usernameVerifyInput() {
//     if (username.value.length >= 1) {
//         username.style.border = "none";
//         document.getElementById("usernameEmpty").style.display = "none";
//         return true;
//     }
// }
//
// function passwordVerifyInput() {
//     if (password.value.length >= 1) {
//         password.style.border = "none";
//         document.getElementById("passwordEmpty").style.display = "none";
//         return true;
//     }
// }
//
// function validateLoginData() {
//     let usernameInput = username.value;
//     let passwordInput = password.value;
//
//     const url = "http://localhost/inloggen-controle";
//
//     let usernameData = `username=${usernameInput}`;
//     let passwordData = `password=${passwordInput}`;
//     let data = usernameData + '&' + passwordData;
//
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded'
//         },
//         body: data
//     })
//         .then(response => response.json())
//
//         //When false invalid login text will be shown
//         .then(json => {
//             if (json) {
//                 document.getElementById("invalidLogin").style.display = "none";
//             } else {
//                 document.getElementById("invalidLogin").style.display = "inline-block";
//             }
//         })
// }