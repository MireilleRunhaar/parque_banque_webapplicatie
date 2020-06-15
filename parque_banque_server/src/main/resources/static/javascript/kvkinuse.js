const kvkInput = document.getElementById("kvkNr");
kvkInput.addEventListener("focusout", validateKvk);

function validateKvk() {

    let input = kvkInput.value;
    const url = "http://localhost/kvk-check";
    let data = 'kvk=${kvkNr}';

    fetch(url, {
        method: 'POST',
        headers: {"Content-Type": 'application/x-www-form-urlencoded'},
        body: data
    })
        .then(response => response.json())
        .then(json => {
            if (json) {
                document.getElementById("validKvk").style.display = "none";
            } else {
                document.getElementById("invalidKvk").style.display = "inline";
            }
        })
}

function displayCompanyInfo(){
    let kvknr = kvkNr.value;

    if(!(/^\d{8}$/i).test(kvknr)) return;

    let kvkLookup = "https://localhost/kvknr?kvknr=${kvkNr}";

        fetch(kvkLookup, {
            method: 'GET',
            headers: { "Content-Type" : 'application/json'},
            body: "kvknr=" + kvknr
        })

            .then(response => response.json())

            .then(json => {
                document.getElementById("kvkNr").value = json.kvknr;
                document.getElementById("btwNr").value = json.btwnr;
                document.getElementById("name").value = json.name;
                document.getElementById("sector").value = json.sector;
            })
}
