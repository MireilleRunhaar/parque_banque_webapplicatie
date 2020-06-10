const form = document.getElementById('form');
const amount = document.getElementById('amount');
const cents = document.getElementById('cents');
const iban = document.getElementById('iban');
const description = document.getElementById('description');
const MAX_TRANSACTION = 10000000;
const MIN_TRANSACTION = 0;

form.addEventListener('submit', (e) => {
    e.preventDefault();
    if(checkInputs() < 1){
        form.submit();
    }

})

function checkInputs(){
    const amountInput = amount.value;
    const centsInput = cents.value;
    const ibanInput = iban.value.trim();
    const descriptionInput = description.value.trim();
    let errors = 0;


    if(!validateTotalAmount(amountInput, centsInput)){
        setErrorFor(amount, "Vul een bedrag in");
        errors++;
    } else{
        setSuccesFor(amount);
    }

    if(ibanInput === '' || ibanInput === null){
        setErrorFor(iban, 'Vul een rekeningnummer in');
        errors++;
    } else if(!validIban(ibanInput)){
        setErrorFor(iban, 'Vul een geldig rekeningnummer in (IBAN)');
        errors++;
    } else {
        setSuccesFor(iban);
    }

    if(descriptionInput.length > 140){
        setErrorFor(description, "U kunt maximaal 140 tekens invoeren");
        errors++;
    } else{
        setSuccesFor(description);
    }

    return errors;
}

function setErrorFor(input, message){
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    small.innerText = message;
    formControl.className = 'form-control error';

}

function setSuccesFor(input){
    const formControl = input.parentElement;
    formControl.className = 'form-control succes';
}

function validIban (iban){
    return /NL\d{2}PARQ0\d{9}/.test(iban);
}




function validateTotalAmount(amountInput, centsInput){
    if(centsInput !== null && amountInput !== null){
        centsInput = Number(centsInput);
        amountInput = Number(amountInput);
        if(Number.isInteger(centsInput) && Number.isInteger(amountInput)){
            const totalAmount = (amountInput * 100) + centsInput;
            console.log(totalAmount);
            return totalAmount > MIN_TRANSACTION && totalAmount <= MAX_TRANSACTION;
        } else{
            return false;
        }

    } else{
        return false;
    }
}