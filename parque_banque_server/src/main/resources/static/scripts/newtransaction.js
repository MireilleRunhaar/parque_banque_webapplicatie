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
    let amountInput = amount.value;
    let centsInput = cents.value;
    const ibanInput = iban.value.trim();
    const descriptionInput = description.value.trim();


    if(validateTotalAmount()){
        setSuccesFor(amount)
    } else{
        setErrorFor(amount, "Vul een bedag in")
    }

    /*if(centsInput === '' || amountInput === null){
        // show error
        // add error class
        setErrorFor(amount, 'Vul een bedrag in');
    } else {
        // add succes class
        setSuccesFor(amount);
    }*/
    
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
    small.innerText = message;
    
    // add error class for CSS
    formControl.className = 'form-control error';
}

function setSuccesFor(input){
    const formControl = input.querySelector('small');
    formControl.className = 'form-control succes';
    
}

function validIban (iban){
    return /NL\d{2}PARQ0\d{9}/.test(iban);
}

function validateTotalAmount(amountInput, centsInput){
    if(centsInput != null && amountInput != null){
        centsInput = Number(centsInput);
        amountInput = Number(amountInput);
        if(Number.isInteger(centsInput) && Number.isInteger(amountInput)){
            const totalAmount = (amountInput * 100) + centsInput;
            if(totalAmount > 0 && totalAmount <= 10000000){
                return true;
            }
        } else{
            return false;
        }

    } else{
        return false;
    }
}