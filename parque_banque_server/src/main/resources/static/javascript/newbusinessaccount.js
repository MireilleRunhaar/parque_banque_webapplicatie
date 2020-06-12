const form = document.getElementById('form');
const company = document.getElementById('select-company');
let button = document.getElementById('button');


company.addEventListener('focusout', checkCompanySelection);

function checkCompanySelection(){
    let companyInput = company.value;
    if(companyInput === null || companyInput === ''){
        setErrorFor(company, "Selecteer een bedrijf");
        button.disabled = true;
    } else{
        setSuccesFor(company);
        button.disabled = false;
    }
}


function setErrorFor(inputField, message){
    let formControl = inputField.parentElement;
    let small = formControl.querySelector('small');
    small.innerText = message;
    formControl.className = formControlError;

}

function setSuccesFor(inputField){
    let formControl = inputField.parentElement;
    formControl.className = formControlSucces;
}