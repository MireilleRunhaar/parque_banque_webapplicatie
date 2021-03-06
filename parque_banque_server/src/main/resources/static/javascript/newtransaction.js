const form = document.getElementById('form');
const amount = document.getElementById('amount');
const cents = document.getElementById('cents');
const iban = document.getElementById('iban');
const description = document.getElementById('description');
const button = document.getElementById('button');
const MAX_TRANSACTION = 10000000;
const MIN_TRANSACTION = 0;
const formControlError = 'form-control error';
const formControlSucces = 'form-control succes';


amount.addEventListener('focusout', checkTransactionAmount);
cents.addEventListener('focusout', checkTransactionAmount);
iban.addEventListener('focusout', checkIbanCreditaccount);
description.addEventListener('focusout', checkDescription);



form.addEventListener('submit', (e) => {

    e.preventDefault();
    checkInputs()
        .then(dataOutput => {
            if (dataOutput){
                confirmWindow();
            }
        })

});

async function checkInputs(){
    let amountInput = amount.value;
    let centsInput = cents.value;
    let totalAmount = validateTotalAmount(amountInput, centsInput);
    if(totalAmount){
        totalAmount = (Number(amountInput) * 100) + Number(centsInput);
    } else {
        setErrorFor(amount, "Vul een bedrag in tot 100.000 euro")
        return false;
    }

    let validAmount = await checkBalanceDebitAccount(totalAmount);
    let validIban = await checkIbanExcist(iban.value);

    let dataOutput = false;
    if(validIban && validAmount){
         dataOutput = true;
    }
    return dataOutput;
}

function confirmWindow(){
    let amountInput = amount.value;
    let centsInput = cents.value;
    let ibanInput = iban.value;

    let message = 'U boekt €' + amountInput + ',' + centsInput+ ' over naar rekeningnummer ' + ibanInput
    Confirm.open({
        title: 'Bevestig verzenden transactie',
        message: message,
        onok: () => {
            document.getElementById('form').submit();
        }
    });
}


function checkTransactionAmount(){
    const amountInput = amount.value;
    const centsInput = cents.value;

    if(!validateTotalAmount(amountInput, centsInput)){
        setErrorFor(amount, "Vul een bedrag in tot 100.000 euro");
    } else{
        let totalAmount = (Number(amountInput) * 100) + Number(centsInput);
        checkBalanceDebitAccount(totalAmount)
            .then(dataOutput =>{
                if(dataOutput){
                    setSuccesFor(amount);
                } else{
                    setErrorFor(amount, "Saldo is ontoereikend");
                }
            });
    }
}

function checkIbanCreditaccount(){
    let ibanInput = iban.value.trim();

   if(!validIban(ibanInput)){
        setErrorFor(iban, 'Vul een geldig rekeningnummer in (IBAN)');
    } else {
        checkIbanExcist(ibanInput)
            .then(dataOutput => {
                if (dataOutput){
                    setSuccesFor(iban);
                } else{
                    setErrorFor(iban, "Rekeningnummer is niet bij ons bekend");
                }
            });

    }
}

function checkDescription(){
    let descriptionInput = description.value.trim();

    if(descriptionInput.length > 140){
        setErrorFor(description, "U kunt maximaal 140 tekens invoeren");
    } else{
        setSuccesFor(description);
    }
}


async function checkBalanceDebitAccount(input){

    const url = "http://localhost/saldo-check";
    let data = `transactionAmount=${input}`;

   let response = await fetch(url,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });

   let dataOutput = await response.json();
   return dataOutput;
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

async function checkIbanExcist(input){

    const url = "http://localhost/iban-check";
    let data = `ibanCredit=${input}`;

    let response = await fetch(url,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });
        let dataOutput = await response.json();
        return dataOutput;

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