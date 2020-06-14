
document.getElementById("kvkNr").addEventListener("focusout", checkIfCompanyKnown);

function checkIfCompanyKnown() {
    let kvknr = document.getElementById("kvkNr").value;
    console.log(kvknr + " ");

    let regex = new RegExp(/^\d{8}$/i);
    console.log(Number.isInteger(nr));
    if(Number.isInteger(nr)) {

        fetch("http://localhost:8080/kvknrinuse", {
            method: 'POST',
            headers: {"Content-Type": 'application/x-www-form-urlencoded'},
            body: "kvknr=" + kvknr
        })
            .then(response => response.json())
            .then(json => { if (json){document.getElementById("kvknrinuse").style.display = "inline";
            } else {
                document.getElementById("kvkBekend").style.display = "none";
            }
            });
    }
}
