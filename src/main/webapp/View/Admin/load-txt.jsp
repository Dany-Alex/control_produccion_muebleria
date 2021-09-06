<%-- 
    Document   : load-txt
    Created on : 1 sep 2021, 18:35:23
    Author     : Artist
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Includes/resources.jsp"/>
<body class="bg-transparent">

    <div class="d-flex justify-content-center align-items-center">
        <div class="col-md-9 text-center text-light">
            <p class="mb-5 mt-5">
            <h1 class="card-heading display-5 text-uppercase "><b>Cargar Datos desde un archivo de texto</b></h1>  
            </p>

            <div class="row border border-1  justify-content-center align-items-center mb-1">
                <form class=" col-md-9 mt-3 mb-3" action="loadTxtController" method="POST" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label for="formFile" class="form-label">Seleccione un archivo de texto con los datos a cargar</label>

                        <input class="form-control" type="file" name="datafile">
                    </div>

                    <button type="submit" name="action" 
                            value="process-txt-file" 
                            class="btn btn-light text-uppercase mb-2 rounded-pill shadow-sm ">  <i class="fas fa-cloud-upload-alt fs-5"></i> Cargar Datos</button>

                </form>     

                <label class="text-success">${msg}</label>
                <label class="text-danger">${err}</label>

                <div class="card bg-transparent ">
                    <div class="card-header border border-1">
                        Resultados de la lectura en el archivo de texto
                    </div>
                    <div class="card-body border border-1 mb-2">
                        <div class="row  justify-content-center align-items-center mb-1">
                            <div class="col  ">
                                Datos leidos correctamente
                                <table class="table table-hover table-striped bg-light mt-3" >
                                    <thead>
                                        <tr>
                                            <th>Linea</th>
                                            <th>Identificador</th>
                                            <th>Datos</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="save" items="${listLoadSave}">
                                            <tr>
                                                <td>${save.getLine()}</td>
                                                <td>${save.getId()}</td>
                                                <td>${save.getData()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                            <div class="col ">
                                Datos no leidos por errores logicos o de escritura
                                <table class="table table-hover table-striped bg-light mt-3" >
                                    <thead>
                                        <tr>
                                            <th>Linea</th>
                                            <th>Identificador</th>
                                            <th>Tipo Error</th>                                
                                            <th>Datos</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="error" items="${listLoadError}">
                                            <tr>
                                                <td>${error.getLine()}</td>
                                                <td>${error.getId()}</td>
                                                <td>${error.getErrorType()}</td>
                                                <td>${error.getData()}</td>
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
    </div> 
</body>

