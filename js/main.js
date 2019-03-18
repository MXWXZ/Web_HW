function mouseOver(obj) {
    obj.style.color = "#f26552";
}

function mouseOut(obj) {
    obj.style.color = "";
}

function signUp() {
    username = document.getElementById("username");
    alert(username.value);
    return false;
}