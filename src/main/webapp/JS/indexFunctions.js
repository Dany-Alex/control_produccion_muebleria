/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadDynamicContent(url, element) {
    var request = new XMLHttpRequest();
    request.open("GET",url,false);
    request.send(null);
    element.innerHTML=request.responseText;
}
function loadLogin() {
    loadDynamicContent("View/Login.jsp",document.getElementById('dynamicContent'));
}
