<%-- 
    Document   : assemble-furniture
    Created on : 21 ago 2021, 17:57:18
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
                <h1 class="card-heading display-3 text-uppercase "><b>Ensambar Mueble</b></h1>  
                </p>

                <div class="row border border-1 ">
                    <div class=" col-md-4">
                        <label for="input-type-piece-select" class="  text-success">${msg}</label>
                        <label for="input-type-piece-select" class="  text-danger">${err}</label>

                        <form class="" action="furnitureController" method="POST">

                            <label for="input-type-Furniture-select" class="  text-light">Tipo de Mueble disponibles</label>

                            <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4 text-uppercase" 
                                    name="input-type-furniture-select">
                                <c:forEach var="furniture" items="${listAllFurnitureType}">
                                    <option value="${furniture.getName()}" class="text-uppercase">${furniture.getName()}</option>
                                </c:forEach>

                            </select>

                            <label for="input-type-piece-checkbox" class="  text-light">Piezas disponibles</label>

                                <table class="table table-hover table-striped bg-light" >
                                    <thead  >
                                        <tr>
                                            <th>Usar</th>
                                            <th>Tipo</th>
                                            <th>Costo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="piece" items="${listAllPieces}">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="piece-checkbox" value="${piece.getId()}">
                                                </td>
                                                <td>${piece.getType()}</td>
                                                <td>${piece.getCost()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                            <div class="form-group d-grid gap-2 mt-4">
                                <button type="submit" name="action" 
                                        value="new-assemble-forniture" 
                                        class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">
                                    <i class="fas fa-hammer"></i> Ensamblar</button>
                            </div>

                        </form>     
                    </div>

                    <div class="col-md-8 mt-4">
                        <table class="table table-hover table-striped bg-light" >
                            <thead  >
                                <tr>
                                    <th>Codigo</th>
                                    <th>Tipo</th>
                                    <th>Costo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="piece" items="${listAllPieces}">
                                    <tr>
                                        <td>${piece.getId()}</td>
                                        <td>${piece.getType()}</td>
                                        <td>${piece.getCost()}</td>

                                        <td>
                                            <form action="pieceController" method="POST">
                                                <input type="hidden" name="id-piece" value="${piece.getId()}">
                                                <button type="submit" name="action" 
                                                        value="update-piece" class="btn btn-info  text-uppercase rounded-pill shadow-sm ">Modificar</button>
                                                <button type="submit" name="action" 
                                                        value="delete-piece" class="btn btn-danger  text-uppercase  rounded-pill shadow-sm ">Eliminar</button>
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
