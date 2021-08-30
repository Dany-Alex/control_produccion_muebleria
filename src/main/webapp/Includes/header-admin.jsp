<%-- 
    Document   : header-admin
    Created on : 25 ago 2021, 1:48:49
    Author     : Artist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>Area de fábrica - Mi Muebleria</title>

    </head>
    <body class="bg-transparent" onload="">

        <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
            <div class="container-fluid">
                <button class="navbar-toggler mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class=" btn btn-outline-light border-0 rounded-pill" >MI MUEBLERIA</a>

                <div class="collapse navbar-collapse" id="navbar">
                    <ul class="navbar-nav me-auto mx-3">
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" 
                               data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reporte</a>
                            <div class="dropdown-menu bg-danger  text-center text-truncate">
                                <div class="btn-group-vertical mx-3">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=assemble-furniture" target="frameMenu">
                                        Ventas 
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=register-furniture" target="frameMenu">
                                        Devoluciones
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu">
                                        Ganancias 
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu">
                                        usuario con más ventas 
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu">
                                        usuario con más ganancias
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu">
                                        mueble más vendido
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=info-furniture" target="frameMenu">
                                        mueble menos vendido
                                    </a>

                                </div>

                            </div>
                        </li>
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" 
                               data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Creación</a>
                            <div class="dropdown-menu bg-danger  text-center text-truncate">
                                <div class="btn-group-vertical mx-3">
                                     <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=register-furniture" target="frameMenu">
                                        usuario
                                    </a>
                                    
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=assemble-furniture" target="frameMenu">
                                        nuevos tipos de muebles 
                                    </a>
                                   

                                </div>

                            </div>
                        </li>

                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" 
                               data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">cancelación</a>
                            <div class="dropdown-menu bg-danger  text-center text-truncate">
                                <div class="btn-group-vertical">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="factoryPrincipalController?menu-factory=register-furniture" target="frameMenu">
                                        usuario
                                    </a>

                                </div>

                            </div>
                        </li>

                    </ul>
                    <ul  class="navbar-nav">
                        <li  class="nav-item  dropstart"> 
                            <a class="btn btn-outline-light border-0 rounded-pill " 
                               href="#" id="navbarDropdownMenuLinkFactory" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <h5 class="mb-0 text-uppercase nav-user-name"> ${userAttribute.getName()} </h5> 
                            </a>
                            <div class="dropdown-menu bg-danger text-white text-center text-truncate" aria-labelledby="navbarDropdownMenuLinkFactory">
                                <div class="nav-user-info mb-2 ">
                                    <img src="https://img.icons8.com/ios-glyphs/24/000000/user--v1.png" alt="" class="user-avatar-md rounded-circle bg bg-light">
                                </div> 
                                <div class="nav-user-info mb-2 ">
                                    <i>${userAttribute.getName()} </i>
                                </div> 
                                <form action="loginController" method="POST">
                                    <button 
                                        type="submit" 
                                        name="action" 
                                        value="logout" 
                                        class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill">Cerrar Sesion</button>
                                </form>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
