<%-- 
    Document   : make-sale
    Created on : 6 sep 2021, 5:56:53
    Author     : Artist
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body class="bg-transparent">

        <div class="d-flex justify-content-center align-items-center">
            <div class="col-md-9 text-center text-light">
                <p class="mb-5 mt-5">
                <h1 class="card-heading display-5 text-uppercase "><b>Relizar venta</b></h1>  
                </p>

                <div class="row border border-1 g-6 ">

                    <div class="col-md-11 mx-4 mt-4">
                        <label for="input-type-piece-select" class="  text-success">${msg}</label>
                        <label for="input-type-piece-select" class=" text-danger">${err}</label>
                        <table class="table table-hover table-striped bg-light" >
                            <thead>
                                <tr>
                                    <th>Mueble</th>
                                    <th>Precio</th>
                                    <th>Accion</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="assembleFurniture" items="${listAllAviableSaleFurniture}">
                                    <tr>
                                        <td>${assembleFurniture.getId()}</td>
                                        <td>${assembleFurniture.getName()}</td>
                                        <td>${assembleFurniture.getPrice()}</td>
                                        <td>
                                            <form action="salePrincipalController" method="GET">
                                                <input type="hidden" name="id-sale" value="${assembleFurniture.getId()}">
                                                <input type="hidden" name="name-sale" value="${assembleFurniture.getName()}">
                                                <input type="hidden" name="price-sale" value="${assembleFurniture.getPrice()}">

                                                <button type="submit" name="menu-sale" 
                                                        value="go-billing" class="btn btn-info  text-uppercase rounded-pill shadow-sm ">Vender</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>



                </div>
            </div>
        </div>
    </body>
</html>