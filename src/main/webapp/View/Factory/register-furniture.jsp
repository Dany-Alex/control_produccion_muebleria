<%-- 
    Document   : register-furniture
    Created on : 18 ago 2021, 14:01:58
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
                <h1 class="card-heading display-3 text-uppercase "><b>Registrar Mueble</b></h1>  
                </p>

                <div class="row border border-1 g-6 ">
                    <div class=" col-sm-3 mx-4 mb-3">
                        <form class=" mt-4 mb-3 " action="pieceController" method="POST">

                            <div class="form-group" >
                                <label for="" class="sr-only  text-light">Tipo Mueble</label>
                                <select class="custom-select btn btn-light text-uppercase rounded-pill shadow-sm " name="">
                                    <option value="A" >Mayor a menor</option>
                                    <option value="B" selected>Menor a Mayor</option>
                                </select>

                                <label for="" class="sr-only mt-1 text-light">Fecha ensambre</label>
                                <input 
                                    id="inputDate"
                                    type="date" 
                                    min="2017-01-01"
                                    required=""
                                    class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                    > 
                                <label for="" class="sr-only mt-1 text-light">Costo</label>
                                <input 
                                    name=""
                                    type="text" 
                                    required="" 
                                    autofocus="" 
                                    class="form-control rounded-pill border-1 border-dark  shadow-sm px-4"
                                    > 
                            </div   >


                            <button type="submit" name="action" value="login" class="btn btn-light text-uppercase mt-3  rounded-pill shadow-sm ">Registrar</button>

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
