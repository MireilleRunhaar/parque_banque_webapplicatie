
var listItems=document.getElementById("mkb-list").getElementsByTagName("td")

for (i = 0; i <listItems.length ; i++) {
    listItems[i].addEventListener("click",activateItem);
}

function activateItem() {
    for (i = 0; i <listItems.length ; i++) {
        listItems[i].classList.remove("active");
    }
    this.classList.add("active");
}