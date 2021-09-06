
function loadDynamicContent(url, element) {
    var request = new XMLHttpRequest();
    request.open("GET", url, false);
    request.send(null);
    element.innerHTML = request.responseText;
}
var path = "View/Factory/";

function msgAlert(msg) {
    if (msg=='') {
        
    }else if (msg==null) {
        
    }else {
        alert(msg);
    }
}

function loadBody() {
    target = document.getElementById('dynamicContentFactory');
    loadDynamicContent(path + "body-factory-principal.jsp", target);
}
function loadEnsambleFurniture() {
    target = document.getElementById('dynamicContentFactory');
    loadDynamicContent(path + "ensamble-furniture.jsp", target);
}
function loadRegisterFurniture() {
    target = document.getElementById('dynamicContentFactory');
    loadDynamicContent(path + "register-furniture.jsp", target);
}
function loadInfoFurniture() {
    target = document.getElementById('dynamicContentFactory');
    loadDynamicContent(path + "info-furniture.jsp", target);
}
function loadAdminPiece() {
    target = document.getElementById('dynamicContentFactory');
    loadDynamicContent(path + "admin-piece.jsp", target);
}
function loadInfoPiece() {
    target = document.getElementById('dynamicContentFactory');
    loadDynamicContent(path + "info-piece.jsp", target);
}