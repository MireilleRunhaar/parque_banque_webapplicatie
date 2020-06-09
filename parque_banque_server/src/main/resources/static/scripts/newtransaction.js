const amount = document.getElementById('amount');
const cents = document.getElementById('cents');
const form = document.getElementById('form');
const errorElement = document.getElementById('amountError');


form.addEventListener('submit', (e) => {
    let messages = [];
    if (amount.value === null || cents.value === null ){
        message.push('vul een bedrag in');

    }

    if(messages.length > 0)
    e.preventDefault();
    errorElement.innerHTML = messages.join(', ');
})
