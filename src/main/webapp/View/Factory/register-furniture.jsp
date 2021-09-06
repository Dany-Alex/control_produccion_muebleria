<%-- 
    Document   : register-furniture
    Created on : 18 ago 2021, 14:01:58
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
                <h1 class="card-heading display-5 text-uppercase "><b>Registrar Mueble</b></h1>  
                </p>

                <div class="row border border-1 g-6 ">
                    <div class=" col-sm-3 mx-4 mb-3">
                         <label class="  text-success">${msg}</label>
                        <label  class="  text-danger">${err}</label>

                        <form class=" mt-4 mb-3 " action="furnitureController" method="POST">

                            <div class="form-group" >

                                <label  class="  text-light">Usuarios</label>

                                <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4 text-uppercase" 
                                        name="input-name-user-select">
                                    <c:forEach var="user" items="${listAllUsers}">
                                        <option value="${user.getName()}" class="text-uppercase">${user.getName()} </option>
                                    </c:forEach>

                                </select>

                                <label  class="  text-light">Tipo de Mueble</label>

                                <select class="form-control form-select rounded-pill border-1 border-dark shadow-sm px-4 text-uppercase" 
                                        name="input-type-furniture-select">
                                    <c:forEach var="furniture" items="${listAllFurnitureType}">
                                        <option value="${furniture.getName()}" class="text-uppercase">${furniture.getName()} </option>
                                    </c:forEach>

                                </select>

                                <label for="" class=" mt-1 text-light">Fecha ensambre</label>
                                <input 
                                    id="inputDate"
                                    name="input-date"
                                    type="date" 
                                    min="2017-01-01"
                                    required=""
                                    class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                    > 

                                <label  class="  text-light mt-2" >Piezas disponibles</label>
                                <div class="overflow-auto" >

                                    <table class="table table-hover table-striped bg-light" >
                                        <thead  >
                                            <tr>
                                                <th>Usar</th>
                                                <th>Tipo</th>
                                                <th>Costo</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="piece" items="${listAllDataAvailable}">
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

                                </div>


                            </div>
                            <button type="submit" name="action" value="register-assemble-furniture" class="btn btn-light text-uppercase mt-3  rounded-pill shadow-sm ">
                                Registrar</button>

                        </form>     
                    </div>
                    <div class="col-sm-8 mt-4">
                        <table class="table table-hover table-striped bg-light" >
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Fecha ensamble</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>

                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </body>
</body>
</html>
