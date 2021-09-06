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
        <div class="">
            <div class="" >
                
                <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
                    <div class="container-fluid">
                        <button class="navbar-toggler mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <h4 class="text-light text-center text-truncate"><i class="fas fa-home"></i> MI MUEBLERIA</a></h4>


                        <div class="collapse navbar-collapse" id="navbar">
                            <ul class="navbar-nav me-auto mx-3">
                                <li class="nav-item dropdown ">
                                    <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-couch"></i> Mueble</a>
                                    <div class="dropdown-menu bg-danger border-4 border-light text-white text-center text-truncate">
                                        <div class="btn-group-vertical mx-2">
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
                                    <div class="dropdown-menu bg-danger border-4 border-light text-white text-center text-truncate ">
                                        <div class="btn-group-vertical mx-2">
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
                            <jsp:include page="/Includes/nav-user-info.jsp"/>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

