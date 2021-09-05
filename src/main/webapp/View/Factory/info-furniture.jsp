<%-- 
    Document   : info-furniture
    Created on : 18 ago 2021, 14:03:57
    Author     : Artist
--%>

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
                <h1 class="card-heading display-3 text-uppercase "><b>Información Mueble</b></h1>  
                </p>

                <div class="row border border-1 g-6 ">

                    <div class="col-sm-8 mx-4 mt-4">
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


                    <div class=" col-sm-3  mb-3">
                        <form class=" mt-4 mb-3 " action="pieceController" method="POST">

                            <div class="form-group" >
                                <div class="input-group-prepend">
                                    <label for="inputSortPiece" class="sr-only  text-light">Ordenar fecha de ensamble</label>
                                    <select class="custom-select btn btn-light text-uppercase mb-2 rounded-pill shadow-sm " name="inputSortPiece">
                                        <option value="A" >Mayor a menor</option>
                                        <option value="B" selected>Menor a Mayor</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" 
                                    name="action" 
                                    value="login" 
                                    class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">Ordenar</button>
                        </form>     
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
