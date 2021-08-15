<%-- 
    Document   : index
    Created on : 12 ago 2021, 20:46:01
    Author     : Artist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria</title>
        <link rel="stylesheet" href="CSS/bootstrap.css" />
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">MI MUEBLERIA</a>
            </div>
        </nav>

        <section class="dynamicContent" id="dynamicContent">

            <div class="form-control">
                <a class="btn btn-info" href="#" onclick="loadLogin()">Iniciar Sesion</a>
            </div>

        </section>

        
        <a class="btn btn-info" href="View/Factory/factoyMenu.jsp">FABRICA</a>
        <a class="btn btn-info" href="View/Sale/saleMenu.jsp">ventas</a>
        <a class="btn btn-info" href="View/Admin/adminMenu.jsp">admin</a>


        <script src="JS/indexFunctions.js" ></script> 
    </body>
    
</html>
