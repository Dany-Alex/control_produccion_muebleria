function validatePass() {
    var p1 = document.getElementById('input-password').value;
    var p2 = document.getElementById('input-confirm-password').value;
    document.getElementById('msg-confirm-pass').innerHTML = "";
    if (p1 === p2) {
        console.log("Las contraseñas coinciden");
        document.getElementById('msg-confirm-pass').innerHTML = "Las contraseñas coinciden";
        document.getElementById("input-confirm-password").classList.add("is-valid");
        document.getElementById("input-confirm-password").classList.remove("is-invalid");
        document.getElementById("msg-confirm-pass").classList.add("text-success");
        document.getElementById("msg-confirm-pass").classList.remove("text-danger");
    } else if (p1 !== p2) {
        console.log("Las contraseñas no coinciden");
        document.getElementById('msg-confirm-pass').innerHTML = "Las contraseñas deben ser iguales";
        document.getElementById("input-confirm-password").classList.remove("is-valid");
        document.getElementById("input-confirm-password").classList.add("is-invalid");
         document.getElementById("msg-confirm-pass").classList.remove("text-success");
        document.getElementById("msg-confirm-pass").classList.add("text-danger");
    }
}

function checkName(name) {
    $.ajax({
        type: "POST",
        url: "userCpmtroller", /* The request will be processed in this file.. */

        beforeSend: function () {
            $("#status").html("geting name...");
        },
        data: "action=validate-user&name=" + name,
        success: function (msg) {
            $("#status").html(msg);
        }
    });
}