<%-- 
    Document   : header-sale
    Created on : 25 ago 2021, 1:43:26
    Author     : Artist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Includes/resources.jsp"/>
        <title>Area de fábrica - Mi Muebleria</title>

    </head>
    <body class="bg-transparent">

        <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
            <div class="container-fluid">
                <button class="navbar-toggler mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class=" btn btn-outline-light border-0 rounded-pill" >MI MUEBLERIA</a>

                <div class="collapse navbar-collapse" id="navbar">
                    <ul class="navbar-nav me-auto mx-3">
                        <li class="nav-item dropdown">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-search"></i> Cosulta</a>
                            <div class="dropdown-menu bg-danger text-center text-truncate">
                                <div class="btn-group-vertical">
                                    <a class="btn btn-outline-light border-0 text-uppercase  rounded-pill" 
                                       href="salePrincipalController?menu-sale=assemble-furniture" target="frameMenuSale">
                                        <i class="fas fa-cash-register"></i> Compras
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase  rounded-pill" 
                                       href="salePrincipalController?menu-sale=register-furniture" target="frameMenuSale">
                                        <i class="fas fa-cart-arrow-down"></i> Devoluciones
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase  rounded-pill" 
                                       href="salePrincipalController?menu-sale=info-furniture" target="frameMenuSale">
                                        <i class="fas fa-couch"></i> Muebles disponibles
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase rounded-pill" 
                                       href="salePrincipalController?menu-sale=ds" target="frameMenuSale">
                                        <i class="fas fa-file-invoice-dollar"></i> detalles Factura
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase rounded-pill" 
                                       href="salePrincipalController?menu-sale=ff" target="frameMenuSale">
                                        <i class="fas fa-calendar-alt"></i> Ventas del dia
                                    </a>


                                </div>


                            </div>
                        </li>
                        <li class="nav-item dropdown ">

                            <a class="btn btn-outline-light border-0 text-uppercase  rounded-pill" 
                               href="salePrincipalController?menu-sale=assemble-furniture" target="frameMenuSale">
                                Registrar Cliente
                            </a>
                            
                        </li>
                        <li class="nav-item dropdown ">

                            <a class="btn btn-outline-light border-0 text-uppercase  rounded-pill" 
                               href="salePrincipalController?menu-sale=make-sale" target="frameMenuSale">
                                Realizar Venta
                            </a>
                            
                        </li>

                    </ul>
                    <jsp:include page="/Includes/nav-user-info.jsp"/>
                </div>
            </div>
        </nav>