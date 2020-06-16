const kvkInput = document.getElementById("kvkNr");
kvkInput.addEventListener("focusout", validateKvk);

function validateKvk() {

    let input = kvkInput.value;
    const url = "http://localhost/kvk-check";
    let data = `kvkNr=${input}`;

    fetch(url, {
        method: 'POST',
        headers: {"Content-Type": 'application/x-www-form-urlencoded'},
        body: data
    })
        .then(response => response.json())
        .then(json => {
            if(json !== null){
                console.log(json.sector);
                document.getElementById("btwNr").value = json.btwNr;
                document.getElementById("name").value = json.name;
                document.getElementById("sectorField").selectedIndex = json.sector.id -1;
            }
        })
}