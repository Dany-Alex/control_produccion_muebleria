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
                    <div class=" col-md-3">
                        <label for="input-type-piece-select" class="  text-success">${msg}</label>
                        <label for="input-type-piece-select" class="  text-danger">${err}</label>

                        <form class="" action="furnitureController" method="POST">

                            <label for="input-type-Furniture-select" class="  text-light">Tipo de Mueble</label>

                            <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4 text-uppercase" 
                                    name="input-type-furniture-select">
                                <c:forEach var="furniture" items="${listAllFurnitureType}">
                                    <option value="${furniture.getName()}" class="text-uppercase">${furniture.getName()} </option>
                                </c:forEach>

                            </select>

                            <label for="input-type-piece-select" class="  text-light">Tipo Pieza</label>

                            <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4" 
                                    name="input-type-piece-select">
                                <c:forEach var="typePiece" items="${listAllTypePiece}">
                                    <option>${typePiece.getNameTypePiece()}</option>
                                </c:forEach>

                            </select>

                            <label for="" class=" mt-1 text-light">Cantidad de piezas</label>
                            <input 
                                name="input-amount-piece"
                                type="number" 
                                required="" 
                                autofocus="" 
                                class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                > 

                            <div class="form-group d-grid gap-2 mt-4">
                                <button type="submit" name="action" 
                                        value="new-assemble-piece" 
                                        class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">
                                    <i class="fas fa-hammer"></i> Ensamblar</button>
                            </div>

                        </form>     
                    </div>

                    <div class="col-md-9 mt-4">
                        <table class="table table-hover table-striped bg-light" >
                            <thead  >
                                <tr>
                                    <th>Tipo Mueble</th>
                                    <th>Pieza</th>
                                    <th>Cantidad</th>
                                    <th>Acciones</th>


                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="assemblagePiece" items="${listAllAssemblagePiece}">
                                    <tr>
                                        <td>${assemblagePiece.getNamefurniture()}</td>
                                        <td>${assemblagePiece.getTypePiece()}</td>
                                        <td>${assemblagePiece.getAmountPieces()}</td>

                                        <td>
                                            <form action="furnitureController" method="POST">
                                                <input type="hidden" name="assemblage-piece-name-furniture" value="${assemblagePiece.getNamefurniture()}">
                                                <input type="hidden" name="assemblage-piece-type-piece" value="${assemblagePiece.getTypePiece()}">

                                                <button type="submit" name="action" 
                                                        value="update-assemblage-piece" class="btn btn-info  text-uppercase rounded-pill shadow-sm ">Modificar</button>
                                                <button type="submit" name="action" 
                                                        value="delete-assemblage-piece" class="btn btn-danger  text-uppercase  rounded-pill shadow-sm ">Eliminar</button>
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
