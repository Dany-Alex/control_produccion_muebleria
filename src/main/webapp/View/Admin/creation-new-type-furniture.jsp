<%-- 
    Document   : creation-new-type-furniture
    Created on : 31 ago 2021, 9:32:58
    Author     : Artist
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Includes/resources.jsp"/>
<body class="bg-transparent">

    <div class="d-flex justify-content-center align-items-center">
        <div class="col-md-9 text-center text-light">
            <p class="mb-5 mt-5">
            <h1 class="card-heading display-3 text-uppercase "><b>Crear Nuevo Tipo de mueble</b></h1>  
            </p>

            <div class="row border border-1 g-6 ">
                <div class=" col-sm-3 mx-4 mb-3">

                    <label class="  text-success">${msg}</label>
                    <label class="  text-danger">${err}</label>

                    <form class=" mb-3 " action="furnitureController" method="POST">

                        <label for="input-type-furniture" class="  text-light">Nombre Mueble</label>
                        <input 
                            name="input-type-furniture"
                            type="text" 
                            required=""
                            class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                            > 

                        <label for="input-price-furniture" class=" mt-2 text-light">Precio</label>
                        <input 
                            name="input-price-furniture"
                            type="text" 
                            required=""
                            class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                            > 

                        <div class="form-group d-grid gap-2 mt-4">
                            <button type="submit" name="action" 
                                    value="new-type-furniture" 
                                    class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">Crear</button>
                        </div>

                    </form>     





                </div>

                <div class="col-sm-8 mt-4">
                    <table class="table table-hover table-striped bg-light" >
                        <thead  >
                            <tr>
                                <th>Tipo</th>
                                <th>Precio</th>
                                <th>Acciones</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="furnitureType" items="${listAllFurnitureType}">
                                <tr>
                                    <td>${furnitureType.getName()}</td>
                                    <td>${furnitureType.getPrice()}</td>

                                    <td>
                                        <form action="pieceController" method="POST">
                                            <input type="hidden" name="id-piece" value="${furnitureType.getName()}">
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

