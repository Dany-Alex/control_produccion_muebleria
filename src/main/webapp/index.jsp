<%-- 
    Document   : index
    Created on : 12 ago 2021, 20:46:01
    Author     : Artist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="msg" class="com.dxa.control_produccion_muebleria.Backend.Model.Clases.Messages.msgError"/>
<jsp:setProperty name="msg" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Includes/resources.jsp"/>
        <script src="Resources/JS/indexFunctions.js" ></script> 

        <title>Muebleria</title>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setHeader("Expires", "0"); // Proxies.

        %>


    </head>
    <body onload="msgAlert('${msg.msg}')">



        <div class="img-bg-wrapper">
            <div class="overlay-img-bg-wrapper">
                <nav class="navbar navbar-expand-lg navbar-dark bg-transparent">
                    <div class="container-fluid">

                        <a class=" btn btn-outline-light border-0 rounded-pill" href="index.jsp">MI MUEBLERIA</a>


                        <div class="row-cols-lg-auto">
                            <a class="btn btn-outline-light text-uppercase border-0 rounded-pill my-sm-0"  onclick="loadLogin()">Iniciar Sesion <i class="fas fa-door-open"></i></a>
                        </div>
                    </div>
                </nav>
                <section class="dynamicContent text-center" id="dynamicContent">
                    <div class=" text-center text-light d-flex justify-content-center">
                        <h1 class="card-heading display-2 text-uppercase mt-5"><b>MI MUEBLERIA</b></h1>  
                    </div> 
                </section>
            </div>
        </div>
        <jsp:include page="/Includes/footer-factory.jsp"/>
