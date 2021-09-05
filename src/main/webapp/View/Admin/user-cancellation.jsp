<%-- 
    Document   : user-cancellation
    Created on : 31 ago 2021, 9:41:22
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
            <div class="col-md-9 text-center text-light ">
                <p class="mb-5 mt-5">
                <h1 class="card-heading display-3 text-uppercase "><b>Cancelar Usuario</b></h1>  
                </p>
                <div class="row justify-content-center align-items-center text-center ">

                    <div class="col-sm-5 border border-1 ">

                        <label class="sr-only  text-success">${msg}</label>
                        <label  class="sr-only  text-danger">${err}</label
                        <form class="" action="userController" method="POST">
                            <table class="table table-hover table-striped bg-light" >
                                <thead  >
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Tipo</th>

                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="user" items="${listAllUsers}">
                                        <tr>
                                            <td>${user.getName()}</td>
                                            <td>${user.getType() == 1 ? 'Ensamblador' : user.getType() == 2 ? 'Vendedor' : user.getType() == 3 ? 'Financiero' : user.getType() == 0 ? 'Cancelado' : ''}</td>
                                            <td>
                                                <form action = "userController" method = "POST">
                                                    <input 
                                                        name="input-name-user"
                                                        type="hidden" 
                                                        required=""
                                                        class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                                        value="${user.getName()}"
                                                        > 
                                                    
                                                    <c:choose>
                                                        <c:when test="${user.getType() != 0 }">
                                                         <button type = "submit" name = "action"
                                                            value = "cancel-user" class="btn btn-outline-danger  text-uppercase rounded-pill shadow-sm ">
                                                        Cancelar</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                         <button type = "submit" name = "action"
                                                            value = "delete-user" class="btn btn-danger  text-uppercase rounded-pill shadow-sm ">
                                                        Eliminar</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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

            </div>
        </div> 

    </body>
</html>