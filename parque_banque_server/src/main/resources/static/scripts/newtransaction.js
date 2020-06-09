const form = document.getElementById('form');
const amount = document.getElementById('amount');
const cents = document.getElementById('cents');
const iban = document.getElementById('iban');
const description = document.getElementById('description');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    
    checkInputs();
    
})

function checkInputs(){
    // de waarden ophalen uit de inputs
    const amountInput = amount.value;
    const centsInput = cents.value;
    const ibanInput = iban.value.trim();
    const descriptionInput = description.value.trim();
    
    
    //amount = niet nul, een getal, minimaal 1 cent, max 100.000,00 euro
    if(amountInput === ''){
        // show error
        // add error class
        setErrorFor(amount, 'Vul een bedrag in');
    } else {
        // add succes class
        setSuccesFor(amount);
    }
    
    if(ibanInput === ''){
        setErrorFor(iban, 'Vul een rekeningnummer in')
    } else if(!validIban(ibanInput)){
        setErrorFor(iban, 'Vul een geldig rekeningnummer in (IBAN)');
    } else {
        setSuccesFor(iban);
    }
}

function setErrorFor(input, message){
    const formControl = input.parentElement; //form-control
    const small = formControl.querySelector('small');
    
    // add error message inside small
    small.innerHTML = message;
    
    // add error class for CSS
    formControl.className = 'form-control error';
}

function setSuccesFor(input){
    const formControl = input.parentElement;
    formControl.className = 'form-control succes';
    
}

function validIban (iban){
    return /NL\d{2}PARQ0\d{9}/.test(iban);
}