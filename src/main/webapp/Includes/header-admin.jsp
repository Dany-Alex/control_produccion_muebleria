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
        <jsp:include page="/Includes/resources.jsp"/>
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
    <body class="bg-transparent" onload="">

        <nav class="navbar navbar-expand-lg navbar-dark bg-danger ">
            <div class="container-fluid">
                <button class="navbar-toggler mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class=" btn btn-outline-light border-0 rounded-pill" > <i class="fas fa-home"></i> MI MUEBLERIA</a>

                <div class="collapse navbar-collapse" id="navbar">
                    <ul class="navbar-nav me-auto mx-3">
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" 
                               data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-chart-bar"></i> Reporte</a>
                            <div class="dropdown-menu bg-danger border-4 border-light text-white text-center text-truncate">
                                <div class="btn-group-vertical mx-3">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=sales-report" target="frameMenuAdmin">
                                        <i class="fas fa-chart-line"></i> Ventas 
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=return-sales-report" target="frameMenuAdmin">
                                        <i class="fas fa-cart-arrow-down"></i> Devoluciones
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=profit-report" target="frameMenuAdmin">
                                        <i class="fas fa-coins"></i> Ganancias 
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=user-most-sales-report" target="frameMenuAdmin">
                                        <i class="fas fa-file-invoice"></i>  usuario con más ventas 
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=user-more-profit-report" target="frameMenuAdmin">
                                        <i class="fas fa-file-invoice"></i>  usuario con más ganancias
                                    </a>
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=best-selling-furniture-report" target="frameMenuAdmin">
                                        <i class="fas fa-file-invoice"></i> mueble más vendido
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=least-sold-furniture-report" target="frameMenuAdmin">
                                        <i class="fas fa-file-invoice"></i> mueble menos vendido
                                    </a>

                                </div>

                            </div>
                        </li>
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" 
                               data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-plus"></i> Creación</a>
                            <div class="dropdown-menu bg-danger border-4 border-light text-white text-center text-truncate">
                                <div class="btn-group-vertical mx-3">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=creation-new-user" target="frameMenuAdmin">
                                        <i class="fas fa-user-plus"></i> nuevo usuario
                                    </a>

                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=creation-new-type-furniture" target="frameMenuAdmin">
                                        <i class="fas fa-couch"></i> nuevos tipos de muebles 
                                    </a>


                                </div>

                            </div>
                        </li>

                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill dropdown-toggle mx-2" 
                               data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-times"></i> cancelación</a>
                            <div class="dropdown-menu bg-danger border-4 border-light text-white text-center text-truncate">
                                <div class="btn-group-vertical">
                                    <a class="btn btn-outline-light border-0 text-uppercase mb-2 rounded-pill " 
                                       href="adminPrincipalController?menu-admin=user-cancellation" target="frameMenuAdmin">
                                        <i class="fas fa-user-minus"></i> usuario
                                    </a>

                                </div>

                            </div>
                        </li>
                        <li class="nav-item dropdown ">
                            <a class="btn btn-outline-light border-0 text-uppercase rounded-pill  mx-2" 
                               href="adminPrincipalController?menu-admin=load-txt" target="frameMenuAdmin"><i class="fas fa-arrow-up"></i> Cargar Datos</a>

                        </li>
                    </ul>
                    <jsp:include page="/Includes/nav-user-info.jsp"/>
                </div>
            </div>
        </nav>
