<%-- 
    Document   : admin-piece
    Created on : 18 ago 2021, 14:08:58
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
        <script src="${pageContext.request.contextPath}/Resources/JS/pieceFunctions.js"></script>
    </head>
    <body class="bg-transparent" onload="msgAlert('')">


        <div class="d-flex justify-content-center align-items-center">
            <div class="col-md-9 text-center text-light">
                <p class="mb-5 mt-5">
                <h1 class="card-heading display-3 text-uppercase "><b>Administrar Piezas</b></h1>  
                </p>

                <div class="row border border-1 g-6 ">
                    <div class=" col-sm-3 mx-4 mb-3">
                        <div class="form-group" >

                            <!-- Button trigger modal -->
                            <button type="button" 
                                    class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm mt-4 mb-3" 
                                    data-bs-toggle="modal" data-bs-target="#staticBackdrop"
                                    data-bs-toggle="tooltip" data-bs-placement="top" title="Para crear un nuevo tipo de pieza este no debe aparecer en la lista de abajo, de lo contrario no se creara"
                                    >
                                Crear nuevo tipo
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered ">
                                    <div class="modal-content bg-danger mx-md-4 my-1 ">
                                        <div class="modal-header ">
                                            <h5 class="modal-title" id="staticBackdropLabel">Crear Nuevo Tipo de Pieza</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>

                                        <div class="row justify-content-center align-items-center text-center">
                                            <div class="col-lg-10">
                                                <table class="table table-hover table-striped bg-light mt-3" >
                                                    <thead>
                                                        <tr>
                                                            <th>Nombre tipo pieza</th>
                                                            <th>Accion</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="typePiece" items="${listAllTypePiece}">
                                                            <tr>
                                                                <td>${typePiece.getNameTypePiece()}</td>
                                                                <td>
                                                                    <form action = "pieceController" method = "POST">
                                                                        <input 
                                                                            name="input-delete-id-type-piece"
                                                                            type="hidden" 
                                                                            required=""
                                                                            class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                                                            value="${typePiece.getNameTypePiece()}"
                                                                            > 
                                                                        <button type = "submit" name = "action"
                                                                                value = "delete-typePiece" class="btn btn-danger  text-uppercase rounded-pill shadow-sm ">
                                                                            Eliminar</button>
                                                                    </form> 
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

                                        </div>


                                        <form action="pieceController" method="POST">
                                            <div class="modal-body">
                                                <div class="row justify-content-center align-items-center text-center">
                                                    <div class="col-md-7 ">

                                                        <label for="input-new-type" class="  text-light">Nuevo Tipo de Pieza</label>
                                                        <div class="form-group " >

                                                            <input 
                                                                name="input-new-type"
                                                                type="text" 
                                                                required="" 
                                                                autofocus="" 
                                                                class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                                                > 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer align-items-center justify-content-center">
                                                <button type="submit" name="action" 
                                                        value="new-type-piece" 
                                                        data-bs-toggle="tooltip" data-bs-placement="top" title="Crear nuevo tipo de pieza"
                                                        class="btn btn-light text-uppercase  rounded-pill shadow-sm ">Crear</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <form class=" mb-3 " action="pieceController" method="POST">

                                <label for="input-type-piece-select" class="  text-light">Tipo Pieza</label>

                                <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4" 
                                        name="input-type-piece-select">
                                    <c:forEach var="typePiece" items="${listAllTypePiece}">
                                        <option>${typePiece.getNameTypePiece()}</option>
                                    </c:forEach>

                                </select>
                                <label for="input-type-cost" class=" mt-2 text-light">Costo</label>
                                <input 
                                    name="input-type-cost"
                                    type="text" 
                                    required=""
                                    class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                    > 
                                </div>

                                <div class="form-group d-grid gap-2 mt-4">
                                    <button type="submit" name="action" 
                                            value="new-piece" 
                                            class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">Crear</button>
                                </div>

                            </form>     
                        </div>

                        <div class="col-sm-8 mt-4">
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
                        <label for="input-type-piece-select" class="  text-success">${msg}</label>
                        <label for="input-type-piece-select" class="  text-danger">${err}</label>


                    </div>
                </div>
            </div>
        </div> 

    </body>
</html>
