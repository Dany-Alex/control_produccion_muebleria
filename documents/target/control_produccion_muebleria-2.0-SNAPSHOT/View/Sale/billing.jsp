<%-- 
    Document   : billing
    Created on : 6 sep 2021, 6:49:22
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
        <script src="${pageContext.request.contextPath}/Resources/JS/adminFunctions.js"></script>
    </head>
    <body class="bg-transparent">

        <div class="d-flex justify-content-center align-items-center">
            <div class="col-md-9 text-center text-light">
                <p class="mb-5 mt-5">
                <h1 class="card-heading display-3 text-uppercase "><b>Crear nuevo Usuario</b></h1>  
                </p>

                <div class="container">



                    <div class="row  justify-content-md-center border border-1">

                        <div class="row mt-3 mb-3">
                            <div class="col-6 col-md-4">
                                <div class="col-12 ">
                                    <label id="msg" class="  text-success">${msg}</label>
                                    <label id="err" class="  text-danger">${err}</label>
                                    <form class="  " action="userController" method="POST">

                                        <label class=" mt-2 text-light">Nit Cliente</label>
                                        <div class="btn-group" >
                                            <input 
                                                name="input-nit-cliente"
                                                type="text" 
                                                required=""
                                                class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                                > 
                                            <button type="submit" name="action" 
                                                    value="new-user" 
                                                    class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm "><i class="fas fa-search"></i></button>
                                        </div>
                                    </form>  

                                    <form class="  " action="userController" method="POST">

                                        <label for="input-name-user" class="mt-2 text-light">Nombre</label>

                                        <input 
                                            name="input-password"
                                            id="input-password"
                                            type="text" 
                                            required=""
                                            class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                            > 


                                        <label for="input-name-user" class=" mt-2 text-light">Direccion</label>

                                        <input 
                                            name="input-confirm-password"
                                            id="input-confirm-password"
                                            type="password" 
                                            required=""
                                            onchange=""
                                            onkeyup=""
                                            onblur=""
                                            class="form-control rounded-pill   shadow-sm px-4"
                                            > 
                                        <label id="msg-confirm-pass" class="">Id Producto</label>

                                        <input 
                                            name="input-confirm-password"
                                            id="input-confirm-password"
                                            type="text" 
                                            required=""
                                            value="${idSale}"
                                            class="form-control rounded-pill   shadow-sm px-4"
                                            > 
                                        <label id="msg-confirm-pass" class="">Nombre Producto</label>
                                        <input 
                                            name="input-confirm-password"
                                            id="input-confirm-password"
                                            type="password" 
                                            required=""
                                            onchange=""
                                            onkeyup="validatePass()"
                                            onblur=""
                                            class="form-control rounded-pill   shadow-sm px-4"
                                            > 
                                        <label id="msg-confirm-pass" class="">Cantidad</label>
                                        <input 
                                            name="input-confirm-password"
                                            id="input-confirm-password"
                                            type="password" 
                                            required=""
                                            onchange=""
                                            onkeyup=""
                                            onblur=""
                                            class="form-control rounded-pill   shadow-sm px-4"
                                            > 
                                        <label id="msg-confirm-pass" class=""></label>
                                    </form>  

                                </div>
                            </div>
                            <div class="col-md-8">
                                <table class="table table-hover table-striped bg-light" >
                                    <thead  >
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Tipo</th>
                                            <th>Estado</th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="user" items="${listAllUsers}">
                                            <tr>
                                                <td>${user.getName()}</td>
                                                <td>${user.getType() == 1 ? 'Ensamblador' : user.getType() == 2 ? 'Vendedor' : user.getType() == 3 ? 'Financiero' : ''}</td>
                                                <td>${user.getStatus() != 0 ? 'Habilitado' : 'Cancelado'}</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>

                    </div>





                </div>
            </div>


        </div> 


    </body>
</html>
