
function loadDynamicContent(url, element) {
    var request = new XMLHttpRequest();
    request.open("GET",url,false);
    request.send(null);
    element.innerHTML=request.responseText;
}
function loadLogin() {
    loadDynamicContent("View/Login.jsp",document.getElementById('dynamicContent'));
}
