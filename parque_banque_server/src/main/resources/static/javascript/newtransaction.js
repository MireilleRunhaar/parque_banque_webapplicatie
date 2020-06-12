const form = document.getElementById('form');
const amount = document.getElementById('amount');
const cents = document.getElementById('cents');
const iban = document.getElementById('iban');
const description = document.getElementById('description');
const button = document.getElementById('button');
const MAX_TRANSACTION = 10000000;
const MIN_TRANSACTION = 0;

amount.addEventListener('focusout', checkTransactionAmount);
cents.addEventListener('focusout', checkTransactionAmount);
iban.addEventListener('focusout',checkIbanCreditaccount);
description.addEventListener('focusout', checkDescription);


function checkTransactionAmount(){
    const amountInput = amount.value;
    const centsInput = cents.value;

    if(!validateTotalAmount(amountInput, centsInput)){
        setErrorFor(amount, "Vul een bedrag in tot 100.000 euro");
    } else{
        let totalAmount = (Number(amountInput) * 100) + Number(centsInput);
        checkBalanceDebitAccount(totalAmount);
    }
}

function checkIbanCreditaccount(){
    const ibanInput = iban.value.trim();

   if(!validIban(ibanInput)){
        setErrorFor(iban, 'Vul een geldig rekeningnummer in (IBAN)');
    } else {
        checkIbanExcist(ibanInput);
    }
}

function checkDescription(){
    const descriptionInput = description.value.trim();

    if(descriptionInput.length > 140){
        setErrorFor(description, "U kunt maximaal 140 tekens invoeren");
    } else{
        setSuccesFor(description);
    }
}


function checkBalanceDebitAccount(input){

    const url = "http://localhost/saldo-check";
    let data = `transactionAmount=${input}`;

    fetch(url,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => response.json())
        .then(json => {
           if(json){
               setSuccesFor(amount);
           } else{
               setErrorFor(amount, "Saldo is ontoereikend");
           }
        })
}


function validateTotalAmount(amountInput, centsInput){
    if(centsInput !== null && amountInput !== null){
        centsInput = Number(centsInput);
        amountInput = Number(amountInput);
        if(Number.isInteger(centsInput) && Number.isInteger(amountInput)){
            const totalAmount = (amountInput * 100) + centsInput;
            return totalAmount > MIN_TRANSACTION && totalAmount <= MAX_TRANSACTION;
        } else{
            return false;
        }
    } else{
        return false;
    }
}

function validIban (iban){
    return /NL\d{2}PARQ0\d{9}/.test(iban);
}

function checkIbanExcist(input){

    const url = "http://localhost/iban-check";
    let data = `ibanCredit=${input}`;

    fetch(url,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => response.json())
        .then(json => {
            if(json){
                setSuccesFor(iban);
            } else{
                setErrorFor(iban, "Rekeningnummer is niet bij ons bekend");
            }
        })
}

function setErrorFor(inputField, message){
    const formControl = inputField.parentElement;
    const small = formControl.querySelector('small');
    small.innerText = message;
    formControl.className = 'form-control error';
    button.disabled = true;
}

function setSuccesFor(inputField){
    const formControl = inputField.parentElement;
    formControl.className = 'form-control succes';
    button.disabled  = false;
}