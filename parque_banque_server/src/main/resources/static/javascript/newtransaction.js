const form = document.getElementById('form');
const amount = document.getElementById('amount');
const cents = document.getElementById('cents');
const iban = document.getElementById('iban');
const description = document.getElementById('description');
const button = document.getElementById('button');
const MAX_TRANSACTION = 10000000;
const MIN_TRANSACTION = 0;
let errors = 0;

form.addEventListener('submit', (e) => {
    errors = 0;
    checkTransactionAmount();
    checkIbanCreditaccount();
    checkDescription();

    if(errors > 0){
        e.preventDefault();
    }
})

function checkTransactionAmount(){
    const amountInput = amount.value;
    const centsInput = cents.value;

    if(!validateTotalAmount(amountInput, centsInput)){
        setErrorFor(amount, "Vul een bedrag in tot 100.000 euro");
        errors++;
    } else{
        let totalAmount = (Number(amountInput) * 100) + Number(centsInput);
        checkBalanceDebitAccount(totalAmount);
    }
}

function checkIbanCreditaccount(){
    const ibanInput = iban.value.trim();

    if(ibanInput === '' || ibanInput === null){
        setErrorFor(iban, 'Vul een rekeningnummer in');
        errors++;
    } else if(!validIban(ibanInput)){
        setErrorFor(iban, 'Vul een geldig rekeningnummer in (IBAN)');
        errors++;
    } else {
        setSuccesFor(iban);
    }
}

function checkDescription(){
    const descriptionInput = description.value.trim();

    if(descriptionInput.length > 140){
        setErrorFor(description, "U kunt maximaal 140 tekens invoeren");
        errors++;
    } else{
        setSuccesFor(description);
    }
}


function setErrorFor(inputField, message){
    const formControl = inputField.parentElement;
    const small = formControl.querySelector('small');
    small.innerText = message;
    formControl.className = 'form-control error';

}

function setSuccesFor(inputField){
    const formControl = inputField.parentElement;
    formControl.className = 'form-control succes';
}

function validIban (iban){
    return /NL\d{2}PARQ0\d{9}/.test(iban);
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
               errors++;
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