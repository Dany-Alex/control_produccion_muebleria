
function loadDynamicContent(url, element) {
    var request = new XMLHttpRequest();
    request.open("GET",url,false);
    request.send(null);
    element.innerHTML=request.responseText;
}
function loadLogin() {
    loadDynamicContent("View/Login.jsp",document.getElementById('dynamicContent'));
}

function msgAlert(msg) {
    if (msg=='UserNull') {
         alert('Tipo de Usuario, Usuario o Password no son validos.\nVuelva intentar');
    } else {
       
    }
}