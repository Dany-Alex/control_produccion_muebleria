<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="bg-transparent">


        <div class="d-flex justify-content-center align-items-center">
            <div class="col-md-9 text-center text-light ">
                <p class="mb-5 mt-5">
                <h1 class="card-heading display-3 text-uppercase "><b>Administrar Piezas</b></h1>  
                </p>
                <div class="row justify-content-center align-items-center text-center ">
                    <div class="col-sm-4 border border-1 ">
                        <div class=" col mx-4 mb-3">
                            <h3 class="card-heading text-uppercase "><b>Modificar Pieza ID: ${updateModelPiece.getId()}</b></h3>  

                            <form class=" mb-3 " action="userController" method="POST">
                                <input 
                                    name="input-update-id-piece"
                                    type="hidden" 
                                    required=""
                                    class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                    value="${updateModelPiece.getId()}"
                                    > 
                                <label for="input-update-type-piece-select" class="sr-only  text-light">Tipo Pieza</label>

                                <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4" 
                                        name="input-update-type-piece-select">
                                    <c:forEach var="typePiece" items="${listAllTypePiece}">
                                        <option ${updateModelPiece.getType()== typePiece.getNameTypePiece() ? 'selected=""' : ''}>${typePiece.getNameTypePiece()}</option>
                                    </c:forEach>

                                </select>

                                <label for="input-update-type-cost" class="sr-only mt-2 text-light">Costo</label>
                                <input 
                                    name="input-update-type-cost"
                                    type="text" 
                                    required=""
                                    class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                    value="${updateModelPiece.getCost()}"

                                    > 

                                <div class="form-group d-grid gap-2 mt-4">
                                    <button type="submit" name="action" 
                                            value="update-id-piece" 
                                            class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">Modificar</button>
                                </div>
                            </form>     
                            <form class=" mb-3 " action="userController" method="POST">
                                <div class="form-group d-grid gap-2 mt-4">
                                    <button type="submit" name="action" 
                                            value="back" 
                                            class="btn btn-outline-danger text-uppercase mb-2 rounded-pill shadow-sm ">Volver</button>
                                </div>
                            </form>     
                        </div>

                    </div>

                    <label for="input-type-piece-select" class="sr-only  text-success">${msg}</label>
                    <label for="input-type-piece-select" class="sr-only  text-danger">${err}</label>


                </div>
            </div>
        </div>


    </body>
</html>