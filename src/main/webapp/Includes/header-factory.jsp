<%-- 
    Document   : header-factory
    Created on : 24 ago 2021, 17:45:06
    Author     : Artist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Includes/resources.jsp"/>
        <script src="${pageContext.request.contextPath}/Resources/JS/factoryFunctions.js"></script>
        <title>Area de fábrica - Mi Muebleria</title>
        <%
            //session=request.getSession();
           response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
           response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setHeader("Expires", "0"); // Proxies.

            if (session.getAttribute("userAttribute") == null) {
                System.out.println("redireccion al index");
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("no redireccion al index - header");
                System.out.println("S -+-" + session.getAttribute("userAttribute"));
                System.out.println("R -+-" + request.getParameter("userAttribute"));

            }
        %>
    </head>
    <body class="bg-transparent" onload="msgAlert('${msgAlert}')">

        <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
            <div class="container-fluid">
                <button class="navbar-toggler mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class=" btn btn-outline-light border-0 rounded-pill" ><i class="fas fa-home"></i> MI MUEBLERIA</a>

                <div class="collapse navbar-collapse" id="navbar">
                    <ul class="navbar-nav me-auto mx-3">
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-couch"></i> Mueble</a>
                            <div class="dropdown-menu bg-danger text-center text-truncate">
                                <div class="btn-group-vertical">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=assemble-furniture" target="frameMenu" >
                                       <i class="fas fa-hammer"></i> Ensamblar
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=register-furniture" target="frameMenu">
                                       <i class="fas fa-check"></i> Registrar
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu" >
                                      <i class="fas fa-info-circle"></i>  Información
                                    </a>

                                </div>



                            </div>
                        </li>


                        <li class="nav-item dropdown">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-puzzle-piece"></i> Pieza</a>
                            <div class="dropdown-menu bg-danger text-center text-truncate">
                                <div class="btn-group-vertical">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=admin-piece" target="frameMenu" >
                                      <i class="fas fa-cog"></i> Administrar
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill" 
                                       href="factoryPrincipalController?menu-factory=info-piece" target="frameMenu">
                                      <i class="fas fa-info-circle"></i> Información
                                    </a>

                                </div>


                            </div>
                        </li>
                    </ul>
                    <ul  class="navbar-nav">
                        <li  class="nav-item  dropstart"> 
                            <a class="btn btn-outline-light border-0 rounded-pill " href="#" id="navbarDropdownMenuLinkFactory" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <h5 class="mb-0 text-uppercase nav-user-name"> ${userAttribute} </h5> 
                            </a>
                            <div class="dropdown-menu bg-danger text-white text-center" aria-labelledby="navbarDropdownMenuLinkFactory">
                                <div class="nav-user-info mb-2 ">
                                    <img src="https://img.icons8.com/ios-glyphs/24/000000/user--v1.png" alt="" class="user-avatar-md rounded-circle bg bg-light">
                                </div> 
                                <div class="nav-user-info mb-2 ">
                                    <i>${userAttribute} </i>
                                </div> 
                                <form action="loginController" method="POST">
                                    <button 
                                        type="submit" 
                                        name="action" 
                                        value="logout"  
                                        class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill text-truncate mx-2">
                                        <i class="fas fa-sign-out-alt"></i> Cerrar Sesion</button>
                                </form>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
