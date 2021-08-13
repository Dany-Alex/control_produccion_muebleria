<%-- 
    Document   : Login
    Created on : 12 ago 2021, 20:40:37
    Author     : Artist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Muebleria</title>
        <link rel="stylesheet" href="../CSS/bootstrap.css" />
    </head>
    <body>
            <form class="row m-2 g-2">

                <div class="col-auto">
                    <label for="inputUserTypeLogin" class="form-label mt-3">Tipo de Usuario</label>
                    <select class="form-select" id="inputUserTypeLogin">
                        <option>Administador</option>
                        <option>Empleado</option>
                        <option>Cliente</option>
                    </select>
                </div>

                <div class="col-auto">
                    <label for="inputUser" class="form-label mt-3">Usuario</label>
                    <input type="text" class="form-control" id="inputUserLogin" placeholder="Usuario">
                </div>
                <div class="col-auto">
                    <label for="inputpassword" class="form-label mt-3">Contraseña</label>
                    <input type="password" class="form-control" id="inputPasswordLogin" placeholder="Contraseña">
                </div>

                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mt-5">Entrar</button>
                </div>
            </form>

    </body>
</html>
