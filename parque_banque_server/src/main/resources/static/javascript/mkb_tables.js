
var listItems=document.getElementById("mkb-list").getElementsByTagName("tr")

for (i = 0; i <listItems.length ; i++) {
    listItems[i].addEventListener("mouseover",activateItem);
}

function activateItem() {
    for (i = 0; i <listItems.length ; i++) {
        listItems[i].classList.remove("active");
    }
    this.classList.add("active");
}